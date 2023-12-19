package com.example.cashmanagerfront.data.repository

import com.example.cashmanagerfront.data.ApiRoute
import com.example.cashmanagerfront.data.ApiService
import com.example.cashmanagerfront.domain.usecase.model.User
import io.ktor.client.statement.HttpResponse

class UserRepository {

    suspend fun createUser(user: User) : HttpResponse {
        return ApiService.post(endpoint = ApiRoute.ROUTE_USERS_REGISTER, body = user)
    }

}