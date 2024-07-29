package com.BGL.test.service.impl;

import com.BGL.test.entity.DistributionInterest;
import com.BGL.test.exception.EntryNotFoundException;
import com.BGL.test.repository.DistributionInterestRepository;
import com.BGL.test.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DistributionInterestService implements EntryService<DistributionInterest> {
    @Autowired
    private DistributionInterestRepository distributionInterestRepository;

    @Override
    public DistributionInterest createEntry(Long id, BigDecimal amount, BigDecimal gst) {
        if (distributionInterestRepository.existsById(id)) {
            throw new EntryNotFoundException("Entry already exist with id " + id);
        }
        DistributionInterest entry = new DistributionInterest();
        entry.setGstAmount(gst);
        entry.setAmount(amount);
        entry.setId(id);
        return distributionInterestRepository.save(entry);
    }

    @Override
    public DistributionInterest updateEntry(Long id,  BigDecimal amount,BigDecimal gst) {
        if (!distributionInterestRepository.existsById(id)) {
            throw new EntryNotFoundException("Entry not found with id " + id);
        }
        DistributionInterest entry = new DistributionInterest();
        entry.setGstAmount(gst);
        entry.setAmount(amount);
        entry.setId(id);
        return distributionInterestRepository.save(entry);
    }

    @Override
    public void deleteEntry(Long id) {
        if (!distributionInterestRepository.existsById(id)) {
            throw new EntryNotFoundException("Entry not found with id " + id);
        }
        try {
            distributionInterestRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting Entry with id " + id, e);
        }
    }

    @Override
    public DistributionInterest getEntry(Long id) {
        return distributionInterestRepository.findById(id)
                .orElseThrow(() -> new EntryNotFoundException("Entry not found with id " + id));

    }

    @Override
    public List<DistributionInterest> getAllEntry() {
        try {
            return distributionInterestRepository.findAll();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error occurred while retrieving entry from the database", e);
        }
    }
}