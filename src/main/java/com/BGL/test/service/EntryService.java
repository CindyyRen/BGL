package com.BGL.test.service;

import com.BGL.test.entity.Entry;
import java.math.BigDecimal;
import java.util.List;

public interface EntryService <T extends Entry>{
    T createEntry(Long id, BigDecimal amount, BigDecimal gst);

    T updateEntry(Long id, BigDecimal amount, BigDecimal gst);

    void deleteEntry(Long id);

    T getEntry(Long id);

    List<T> getAllEntry();
}
