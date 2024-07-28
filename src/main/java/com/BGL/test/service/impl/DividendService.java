package com.BGL.test.service.impl;

import com.BGL.test.entity.Dividend;
import com.BGL.test.exception.EntryNotFoundException;
import com.BGL.test.repository.DividendRepository;
import com.BGL.test.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DividendService implements EntryService<Dividend> {
    @Autowired
    private DividendRepository dividendRepository;

    @Override
    public Dividend createEntry(Long id, BigDecimal amount, BigDecimal gst) {
        if (dividendRepository.existsById(id)) {
            throw new EntryNotFoundException("Entry already exist with id " + id);
        }
        Dividend entry = new Dividend();
        entry.setGstAmount(gst);
        entry.setAmount(amount);
        entry.setId(id);
        return dividendRepository.save(entry);

    }

    @Override
    public Dividend updateEntry(Long id, BigDecimal amount, BigDecimal gst) {
        if (!dividendRepository.existsById(id)) {
            throw new EntryNotFoundException("Entry not found with id " + id);
        }
        Dividend entry = new Dividend();
        entry.setGstAmount(gst);
        entry.setAmount(amount);
        entry.setId(id);
        return dividendRepository.save(entry);
    }

    @Override
    public void deleteEntry(Long id) {
        if (!dividendRepository.existsById(id)) {
            throw new EntryNotFoundException("Entry not found with id " + id);
        }
        try {
            dividendRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting Entry with id " + id, e);
        }
    }

    @Override
    public Dividend getEntry(Long id) {
        return dividendRepository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException("Entry not found with id " + id));

    }

    @Override
    public List<Dividend> getAllEntry() {
        try {
            return dividendRepository.findAll();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error occurred while retrieving entry from the database", e);
        }
    }
}
