package com.BGL.test.repository;

import com.BGL.test.entity.BasicBankEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicBankEntryRepository extends EntryRepository<BasicBankEntry>{
}
