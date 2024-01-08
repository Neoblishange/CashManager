package com.example.cashmanagerfront.ui.screens.Settings

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.cashmanagerfront.data.ApiService
import com.example.cashmanagerfront.domain.usecase.model.TransactionCallback
import com.example.cashmanagerfront.domain.usecase.model.User
import com.example.cashmanagerfront.ui.utils.Constant
import com.example.cashmanagerfront.ui.utils.Strings
import com.google.gson.JsonParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingsViewModel(context: Context) : ViewModel() {
    var name: MutableState<String> = mutableStateOf("")
    var password: MutableState<String> = mutableStateOf("")
    var transactions: MutableList<TransactionCallback> = mutableListOf<TransactionCallback>()
    var hadTransaction : MutableState<Boolean> = mutableStateOf(true)

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    fun login(context: Context) {
        var user: User? = null
        user = User(name = name.value, password = password.value)

        val call: Call<User> = ApiService.userRepository.login(user)

        call!!.enqueue(object : Callback<User?> {
            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                if (response.isSuccessful) {

                    with(sharedPreferences.edit()) {
                        putString("token", response.body()?.data)
                        putString("name", name.value)
                        apply()
                    }

                    Constant.IS_AUTHENTICATED.value = true

                } else {
                    val res: String? = response.errorBody()!!.string()

                    val json = JsonParser().parse(res)
                    val dataValue = json.asJsonObject.get("data").asString
                    Toast.makeText(context, dataValue, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User?>, t: Throwable) {
                Toast.makeText(context, "Error : " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getAllTransactions(context: Context) {

        val token: String? = "Bearer " + getTokenFromPreferences()

        val call: Call<List<TransactionCallback?>?>? =
            ApiService.transactionRepository.getAllTransactions(
                token!!
            )


        call!!.enqueue(object : Callback<List<TransactionCallback?>?> {
            override fun onResponse(
                call: Call<List<TransactionCallback?>?>,
                response: Response<List<TransactionCallback?>?>
            ) {
                if (response.isSuccessful) {
                    transactions = response.body() as MutableList<TransactionCallback>
                    hadTransaction.value = true
                } else {
                    hadTransaction.value = false
                    val res: String? = response.errorBody()!!.string()
                    val json = JsonParser().parse(res)
                    val dataValue = json.asJsonObject.get("data").asString
                    Toast.makeText(context, dataValue, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<TransactionCallback?>?>, t: Throwable) {
                Toast.makeText(context, "Error : " + t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun logout() {
        with(sharedPreferences.edit()) {
            clear()
            apply()
            Constant.IS_AUTHENTICATED.value = false
        }
    }

    fun getUsernameFromPreferences(): String? {
        return sharedPreferences.getString("name", "")
    }

    fun getTokenFromPreferences(): String? {
        return sharedPreferences.getString("token", "")
    }

}