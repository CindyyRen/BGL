package com.BGL.test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_basicbankentry", schema = "mydb")
public class BasicBankEntry extends Entry {
}
