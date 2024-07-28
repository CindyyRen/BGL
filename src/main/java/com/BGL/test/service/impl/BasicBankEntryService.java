package com.BGL.test.service.impl;

import com.BGL.test.entity.BasicBankEntry;
import com.BGL.test.exception.EntryNotFoundException;
import com.BGL.test.repository.BasicBankEntryRepository;
import com.BGL.test.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BasicBankEntryService implements EntryService<BasicBankEntry> {
    @Autowired
    private BasicBankEntryRepository basicBankEntryRepository;
    @Override
    public BasicBankEntry createEntry(Long id, BigDecimal amount, BigDecimal gst) {
        if (basicBankEntryRepository.existsById(id)) {
            throw new EntryNotFoundException("Entry already exist with id " + id);
        }
        BasicBankEntry entry = new BasicBankEntry();
        entry.setGstAmount(gst);
        entry.setAmount(amount);
        entry.setId(id);
        return basicBankEntryRepository.save(entry);
    }

    @Override
    public BasicBankEntry updateEntry(Long id, BigDecimal amount, BigDecimal gst) {
        if (!basicBankEntryRepository.existsById(id)) {
            throw new EntryNotFoundException("Entry not found with id " + id);
        }
        BasicBankEntry entry = new BasicBankEntry();
        entry.setGstAmount(gst);
        entry.setAmount(amount);
        entry.setId(id);
        return basicBankEntryRepository.save(entry);
    }

    @Override
    public void deleteEntry(Long id) {
        if (!basicBankEntryRepository.existsById(id)) {
            throw new EntryNotFoundException("Entry not found with id " + id);
        }
        try{
            basicBankEntryRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Error occurred while deleting Entry with id " + id, e);
        }
    }

    @Override
    public BasicBankEntry getEntry(Long id) {
        return basicBankEntryRepository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException("Entry not found with id " + id));
    }

    @Override
    public List<BasicBankEntry> getAllEntry() {
        try {
            return basicBankEntryRepository.findAll();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error occurred while retrieving entry from the database", e);
        }
    }
}
