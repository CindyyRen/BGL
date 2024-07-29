package com.BGL.test.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@MappedSuperclass // JPA 注解，用于标识一个类作为其他实体类的基类。
@Getter
@Setter
public abstract class Entry {
    @Id
    private Long id;
    private BigDecimal amount;

    private BigDecimal gstAmount;

}