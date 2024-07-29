package com.BGL.test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_entrytransaction", schema = "mydb")
public class EntryTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "taccId",nullable = true)
    private Long taccId;

    @Column(name = "entryId",nullable = false)
    private Long entryId;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Double amount;

    @Column(name = "transactionDate",nullable = false)
    private Date transactionDate;

    @Column(name = "fundId",nullable = false)
    private String fundId;

    @CreationTimestamp //当实体对象首次持久化（即插入数据库）时，自动设置当前时间戳。
    private LocalDateTime date_created;
    @UpdateTimestamp //每当实体对象被更新时，自动设置当前时间戳。
    private LocalDateTime last_updated;
}
