package com.example.cashmanagerfront.data.repository

import com.example.cashmanagerfront.data.ApiRoute
import com.example.cashmanagerfront.domain.usecase.model.Data
import com.example.cashmanagerfront.domain.usecase.model.Payout
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TransactionRepository {

    @POST(ApiRoute.ROUTE_PAYOUT)
    fun payout(@Body payout: Payout): Call<Data?>?
}