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

    /*private val client = HttpClient()

    private const val AUTH_HEADER = "Authorization"

    @OptIn(InternalAPI::class)
    suspend fun makeRequest(
        endpoint: String,
        method: HttpMethod,
        token: String? = null,
        bodyRequest: Any? = null
    ): HttpResponse {
        val url = "$API_BASE_URL$endpoint"
        val headers = mutableMapOf<String, String>()

//        token?.let { headers[AUTH_HEADER] = "Bearer $it" }

        return client.request(url) {
            this.method = method
            headers.forEach { (key, value) ->
                this.headers.append(key, value)
            }

            if (method != HttpMethod.Get && bodyRequest != null) {
                this.body = bodyRequest
            }
        }
    }

    suspend fun get(endpoint: String, token: String? = null): HttpResponse {
        return makeRequest(endpoint, HttpMethod.Get, token)
    }

    suspend fun post(endpoint: String, token: String? = null, body: Any): HttpResponse {
        return makeRequest(endpoint, HttpMethod.Post, token, body)
    }

    suspend fun put(endpoint: String, token: String? = null, body: Any): HttpResponse {
        return makeRequest(endpoint, HttpMethod.Put, token, body)
    }

    suspend fun delete(endpoint: String, token: String? = null): HttpResponse {
        return makeRequest(endpoint, HttpMethod.Delete, token)
    }*/

}