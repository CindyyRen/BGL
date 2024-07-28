package com.BGL.test.service;

import com.BGL.test.entity.EntryTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EntrytransactionService {
    EntryTransaction createEntrytransaction(EntryTransaction entrytransaction);

    EntryTransaction updateEntrytransaction(Long id, EntryTransaction entrytransaction);

    void deleteEntrytransaction(Long id);

    EntryTransaction getEntrytransaction(Long id);

    Page<EntryTransaction> getAllEntrytransaction(Pageable pageable);
}
