package com.example.cashmanagerfront.ui.screens.Settings

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.cashmanagerfront.data.ApiService
import com.example.cashmanagerfront.domain.usecase.model.Data
import com.example.cashmanagerfront.domain.usecase.model.Transaction
import com.example.cashmanagerfront.domain.usecase.model.User
import com.example.cashmanagerfront.ui.utils.Constant
import com.example.cashmanagerfront.ui.utils.Strings
import com.google.gson.JsonParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingsViewModel(context: Context): ViewModel() {
    var name: MutableState<String> = mutableStateOf("")
    var password: MutableState<String> = mutableStateOf("")
    var transactions : MutableList<Transaction> = mutableListOf<Transaction>()

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    fun login(context: Context){
        var user: User? = null
        user = User(name = name.value, password = password.value)

        val call: Call<User> = ApiService.userRepository.login(user)

        call!!.enqueue(object : Callback<User?> {
            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                if(response.isSuccessful) {
                    with(sharedPreferences.edit()) {
                        putString("token", response.body()?.token)
                        putString("name", response.body()?.name)
                        apply()
                    }

                    Toast.makeText(context, Strings.LOGIN_WELCOME + response.body()?.name?.uppercase() + " !", Toast.LENGTH_SHORT).show()
                    Constant.IS_AUTHENTICATED.value = true

                } else {
                    val res : String? = response.errorBody()!!.string()
                    val json = JsonParser().parse(res)
                    val dataValue = json.asJsonObject.get("data").asString
                    Toast.makeText(context, dataValue, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User?>, t: Throwable) {
                Toast.makeText(context, "Error found is : " + t.message, Toast.LENGTH_SHORT).show()
                val resp = "Error found is : " + t.message
            }
        })
    }

    fun getAllTransactions() {

        val token: String? = getTokenFromPreferences()

        val call: Call<List<Transaction?>?>? = token?.let {
            ApiService.transactionRepository.getAllTransactions(
                it
            )
        }

        call!!.enqueue(object : Callback<List<Transaction?>?> {
            override fun onResponse(
                call: Call<List<Transaction?>?>,
                response: Response<List<Transaction?>?>
            ) {
                if(response.isSuccessful) {
                    transactions = response.body() as MutableList<Transaction>
                } else {

                }
            }

            override fun onFailure(call: Call<List<Transaction?>?>, t: Throwable) {

            }
        })
    }

    fun logout() {
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }

    fun getUsernameFromPreferences(): String? {
        return sharedPreferences.getString("name", "")
    }

    fun getTokenFromPreferences(): String? {
        return sharedPreferences.getString("token", "")
    }

}