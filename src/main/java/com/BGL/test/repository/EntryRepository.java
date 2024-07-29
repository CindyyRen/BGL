package com.BGL.test.repository;

import com.BGL.test.entity.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntryRepository<T extends Entry> extends JpaRepository<T,Long> {
    //EntryRepository 可以处理任何继承自 Entry 的实体类，而不仅仅是 Entry 本身
}
