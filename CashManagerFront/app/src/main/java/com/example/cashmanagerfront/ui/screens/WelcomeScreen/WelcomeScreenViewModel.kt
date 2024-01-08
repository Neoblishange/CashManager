package com.example.cashmanagerfront.ui.screens.WelcomeScreen

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cashmanagerfront.data.ApiService
import com.example.cashmanagerfront.domain.usecase.model.Data
import com.example.cashmanagerfront.domain.usecase.model.User
import com.google.gson.JsonParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WelcomeScreenViewModel : ViewModel() {
    var name: MutableState<String> = mutableStateOf("")
    var password: MutableState<String> = mutableStateOf("")
    var message: MutableState<String> = mutableStateOf("")

    fun createUser(context: Context) {
        var user: User? = null
        user = User(name = name.value, password = password.value)

        val call: Call<Data?>? = ApiService.userRepository.createUser(user)

        call!!.enqueue(object : Callback<Data?> {
            override fun onResponse(call: Call<Data?>, response: Response<Data?>) {
                if(response.isSuccessful) {
                    val res: String? = response.body()?.data
                    Toast.makeText(context, res, Toast.LENGTH_SHORT).show()
                    val resp = "Response Code : " + response.code() + "\n Body :" + res
                    message.value = resp
                } else {
                    val res : String? = response.errorBody()!!.string()
                    val json = JsonParser().parse(res)
                    val dataValue = json.asJsonObject.get("data").asString
                    Toast.makeText(context, dataValue, Toast.LENGTH_SHORT).show()
                    message.value = dataValue
                }
            }

            override fun onFailure(call: Call<Data?>, t: Throwable) {
                Toast.makeText(context, "Error found is : " + t.message, Toast.LENGTH_SHORT).show()
                val resp = "Error found is : " + t.message
                message.value = resp
            }
        })
    }


}