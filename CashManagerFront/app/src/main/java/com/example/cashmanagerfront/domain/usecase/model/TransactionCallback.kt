package com.example.cashmanagerfront.domain.usecase.model

import android.accounts.Account

class TransactionCallback(
    val account: Payout,
    val amount: Float,
    val id: Int
)