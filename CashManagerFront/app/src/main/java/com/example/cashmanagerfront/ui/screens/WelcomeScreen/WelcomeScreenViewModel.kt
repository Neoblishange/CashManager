package com.example.cashmanagerfront.ui.screens.WelcomeScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cashmanagerfront.data.repository.UserRepository
import com.example.cashmanagerfront.domain.usecase.model.User
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class WelcomeScreenViewModel : ViewModel() {
    var name: MutableState<String> = mutableStateOf("")
        var password: MutableState<String> = mutableStateOf("")
        suspend fun createUser(): HttpResponse? {
            var user: User? = null
            var userResponse: HttpResponse? = null

            user = User(name = name.value, password = password.value)
            println(user.password)
        withContext(Dispatchers.IO) {
            try {
                userResponse = UserRepository().createUser(user)
                println("user -----> $userResponse")
            } catch (e: Exception) {
                println("exception ----> $e")
                null
            }
        }
        return userResponse
    }


}