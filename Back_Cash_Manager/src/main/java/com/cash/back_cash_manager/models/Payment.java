package com.cash.back_cash_manager.models;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Hidden
public class Payment {

    private int accountNumber;
    private BigDecimal amount;
}
