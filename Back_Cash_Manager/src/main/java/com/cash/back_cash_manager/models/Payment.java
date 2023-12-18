package com.cash.back_cash_manager.models;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Payment {

    private int accountNumber;
    private BigDecimal amount;
}
