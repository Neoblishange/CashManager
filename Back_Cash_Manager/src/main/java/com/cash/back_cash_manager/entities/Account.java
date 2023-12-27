package com.cash.back_cash_manager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @NotBlank
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @NotBlank
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
}

