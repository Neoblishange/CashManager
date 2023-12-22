package com.example.cashmanagerfront.data.repository

import com.example.cashmanagerfront.data.ApiRoute
import com.example.cashmanagerfront.domain.usecase.model.Data
import com.example.cashmanagerfront.domain.usecase.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRepository {

    @POST(ApiRoute.ROUTE_USERS_REGISTER)
    fun createUser(@Body user: User?): Call<Data?>?

    @POST(ApiRoute.ROUTE_USERS_LOGIN)
    fun login(@Body user: User?): Call<User>
}