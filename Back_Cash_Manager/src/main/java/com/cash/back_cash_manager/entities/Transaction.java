package com.cash.back_cash_manager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "transactions")
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Transaction {
    @Id
    @NotBlank
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotBlank
    @JoinColumn(name = "accountId", nullable = false)
    public Account account;
    @NotBlank
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    public Transaction(Account account, BigDecimal amount) {
        this.account = account;
        this.amount = amount;
    }
}
