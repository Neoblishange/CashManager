package com.cash.back_cash_manager.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;

@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_number", unique = true)
    private String account_number;
    @Column(name = "amount")
    private String amount;
    @Column(name = "date")
    private DateFormat date;
}
