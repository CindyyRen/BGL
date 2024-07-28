package com.BGL.test.service.impl;

import com.BGL.test.entity.Investment;
import com.BGL.test.exception.EntryNotFoundException;
import com.BGL.test.repository.InvestmentRepository;
import com.BGL.test.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InvestmentService implements EntryService<Investment> {
    @Autowired
    private InvestmentRepository investmentRepository;

    @Override
    public Investment createEntry(Long id, BigDecimal amount, BigDecimal gst) {
        if (investmentRepository.existsById(id)) {
            throw new EntryNotFoundException("Entry already exist with id " + id);
        }
        Investment entry = new Investment();
        entry.setGstAmount(gst);
        entry.setAmount(amount);
        entry.setId(id);
        return investmentRepository.save(entry);
    }

    @Override
    public Investment updateEntry(Long id, BigDecimal amount, BigDecimal gst) {
        if (!investmentRepository.existsById(id)) {
            throw new EntryNotFoundException("Entry not found with id " + id);
        }
        Investment entry = new Investment();
        entry.setGstAmount(gst);
        entry.setAmount(amount);
        entry.setId(id);
        return investmentRepository.save(entry);
    }

    @Override
    public void deleteEntry(Long id) {
        if (!investmentRepository.existsById(id)) {
            throw new EntryNotFoundException("Entry not found with id " + id);
        }
        try {
            investmentRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting Entry with id " + id, e);
        }
    }

    @Override
    public Investment getEntry(Long id) {
        return investmentRepository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException("Entry not found with id " + id));

    }

    @Override
    public List<Investment> getAllEntry() {
        try {
            return investmentRepository.findAll();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error occurred while retrieving entry from the database", e);
        }
    }
}
