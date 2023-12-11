package com.example.cashmanagerfront.domain.usecase.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Article(val name: String, val price: Double,  val quantity: MutableState<Int> = mutableStateOf(0))
