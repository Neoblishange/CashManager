package com.example.cashmanagerfront.data

import com.example.cashmanagerfront.data.ApiRoute.API_BASE_URL
import com.example.cashmanagerfront.data.repository.TransactionRepository
import com.example.cashmanagerfront.data.repository.UserRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object ApiService {

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val userRepository: UserRepository = retrofit.create(UserRepository::class.java)

    val transactionRepository: TransactionRepository = retrofit.create(TransactionRepository::class.java)

}
