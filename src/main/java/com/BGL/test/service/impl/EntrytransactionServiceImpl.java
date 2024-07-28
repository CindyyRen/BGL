package com.BGL.test.service.impl;

import com.BGL.test.component.EntryServiceFactory;
import com.BGL.test.entity.Entry;
import com.BGL.test.entity.EntryTransaction;
import com.BGL.test.exception.EntrytransactionNotFoundException;
import com.BGL.test.repository.EntrytransactionRepository;
import com.BGL.test.service.EntryService;
import com.BGL.test.service.EntrytransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class EntrytransactionServiceImpl implements EntrytransactionService {
    @Autowired
    EntrytransactionRepository entrytransactionRepo;

    @Autowired
    EntryServiceFactory entryServiceFactory;

    @Transactional
    @Override
    public EntryTransaction createEntrytransaction(EntryTransaction entrytransaction) {
        String entryType = entrytransaction.getType();
        EntryService<? extends Entry> entryService = entryServiceFactory.getService(entryType);
        Double amount = entrytransaction.getAmount();
        entryService.createEntry(entrytransaction.getEntryId(), BigDecimal.valueOf(amount), BigDecimal.valueOf(0.1 * amount));

        return entrytransactionRepo.save(entrytransaction);
    }
    @Transactional
    @Override
    public EntryTransaction updateEntrytransaction(Long id, EntryTransaction entrytransaction) {
        String entryType = entrytransaction.getType();
        EntryService<? extends Entry> entryService = entryServiceFactory.getService(entryType);
        Double amount = entrytransaction.getAmount();
        entryService.updateEntry(entrytransaction.getEntryId(), BigDecimal.valueOf(amount), BigDecimal.valueOf(0.1 * amount));

        if (!entrytransactionRepo.existsById(id)) {
            throw new EntrytransactionNotFoundException("Entry Transaction not found with id " + entrytransaction.getId());
        }
        LocalDateTime dateCreated = entrytransactionRepo.findById(id).get().getDate_created();
        entrytransaction.setId(id);
        entrytransaction.setDate_created(dateCreated);
        return entrytransactionRepo.save(entrytransaction);
    }
    @Transactional
    @Override
    public void deleteEntrytransaction(Long id) {
        if (!entrytransactionRepo.existsById(id)) {
            throw new EntrytransactionNotFoundException("Entry Transaction not found with id " + id);
        }
        EntryTransaction entrytransaction = entrytransactionRepo.findById(id).get();
        String entryType = entrytransaction.getType();
        EntryService<? extends Entry> entryService = entryServiceFactory.getService(entryType);
        entryService.deleteEntry(entrytransaction.getEntryId());
        try {
            entrytransactionRepo.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting Entry Transaction with id " + id, e);
        }
    }

    @Override
    public EntryTransaction getEntrytransaction(Long id) {
        return entrytransactionRepo.findById(id)
                .orElseThrow(() -> new EntrytransactionNotFoundException("Entry Transaction not found with id " + id));
    }

    @Override
    public Page<EntryTransaction> getAllEntrytransaction(Pageable pageable) {
        return entrytransactionRepo.findAll(pageable);
    }
}
