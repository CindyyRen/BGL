package com.BGL.test.repository;

import com.BGL.test.entity.EntryTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrytransactionRepository extends JpaRepository<EntryTransaction,Long> {
    Page<EntryTransaction> findAll(Pageable pageable);
}
