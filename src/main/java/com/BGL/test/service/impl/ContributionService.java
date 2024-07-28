package com.BGL.test.service.impl;

import com.BGL.test.entity.Contribution;
import com.BGL.test.exception.EntryNotFoundException;
import com.BGL.test.repository.ContributionRepository;
import com.BGL.test.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContributionService implements EntryService<Contribution> {
    @Autowired
    private ContributionRepository contributionRepository;

    @Override
    public Contribution createEntry(Long id, BigDecimal amount, BigDecimal gst) {
        if (contributionRepository.existsById(id)) {
            throw new EntryNotFoundException("Entry already exist with id " + id);
        }
        Contribution entry = new Contribution();
        entry.setGstAmount(gst);
        entry.setAmount(amount);
        entry.setId(id);
        return contributionRepository.save(entry);
    }
    @Override
    public Contribution updateEntry(Long id, BigDecimal amount, BigDecimal gst) {
        if (!contributionRepository.existsById(id)) {
            throw new EntryNotFoundException("Entry not found with id " + id);
        }
        Contribution entry = new Contribution();
        entry.setGstAmount(gst);
        entry.setAmount(amount);
        entry.setId(id);
        return contributionRepository.save(entry);
    }

    @Override
    public void deleteEntry(Long id) {
        if (!contributionRepository.existsById(id)) {
            throw new EntryNotFoundException("Entry not found with id " + id);
        }
        try {
            contributionRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting Entry with id " + id, e);
        }
    }

    @Override
    public Contribution getEntry(Long id) {
        return contributionRepository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException("Entry not found with id " + id));

    }

    @Override
    public List<Contribution> getAllEntry() {
        try {
            return contributionRepository.findAll();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error occurred while retrieving entry from the database", e);
        }
    }
}