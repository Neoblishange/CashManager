package com.cash.back_cash_manager.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "transactions")
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "accountId", nullable = false)
    public Account account;
    @Column(name = "amount")
    private BigDecimal amount;

    public Transaction(Account account, BigDecimal amount) {
        this.account = account;
        this.amount = amount;
    }
}
