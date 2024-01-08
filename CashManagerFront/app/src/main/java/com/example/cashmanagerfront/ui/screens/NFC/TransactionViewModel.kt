package com.example.cashmanagerfront.ui.screens.NFC

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.nfc.NfcAdapter
import android.provider.Settings
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.cashmanagerfront.data.ApiService
import com.example.cashmanagerfront.domain.usecase.model.Data
import com.example.cashmanagerfront.domain.usecase.model.Payout
import com.google.gson.JsonParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionViewModel(context: Context): ViewModel() {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    fun postPayout(stateOfPaiement: MutableState<StateOfPaiement>, total: String, accountNumber: String, context: Context) {
        stateOfPaiement.value = StateOfPaiement.PENDING

        var payout = Payout(accountNumber = accountNumber, amount = total.toFloat())

        var token: String? = "Bearer " + getTokenFromPreferences()

        val call: Call<Data?>? = ApiService.transactionRepository.payout(token!!, payout)

        call!!.enqueue(object : Callback<Data?> {
            override fun onResponse(call: Call<Data?>?, response: Response<Data?>?) {
                if(response!!.isSuccessful) {
                    stateOfPaiement.value = StateOfPaiement.ACCEPTED
                } else {
                    stateOfPaiement.value = StateOfPaiement.REFUSED

                    val res: String? = response.errorBody()!!.string()
                    val json = JsonParser().parse(res)
                    val dataValue = json.asJsonObject.get("data").asString
                    Toast.makeText(context, dataValue, Toast.LENGTH_SHORT).show()                }
            }

            override fun onFailure(call: Call<Data?>?, t: Throwable) {
                stateOfPaiement.value = StateOfPaiement.REFUSED
                Toast.makeText(context, "Error : " + t.message, LENGTH_LONG).show()

            }
        })
    }

    fun checkIfAmountIsEqualsToTotalQR(stateOfPaiement: MutableState<StateOfPaiement>, total: String, amountQr: String, accountNumber: String, context: Context) {
        stateOfPaiement.value = StateOfPaiement.PENDING_DATA
        if(total.toFloat() == amountQr.toFloat()) {
            stateOfPaiement.value = StateOfPaiement.QR_CORRECT
            postPayout(stateOfPaiement, total = total, accountNumber, context = context)
        } else {
            stateOfPaiement.value = StateOfPaiement.QR_ERROR
        }
    }

    fun isNfcAvailable(context: Context, nfcAdapter: NfcAdapter, stateOfPaiement: MutableState<StateOfPaiement>, accountNumber: MutableState<String>, total: String) {
        if (nfcAdapter.isEnabled) {
            startNfcScanner(context, nfcAdapter, stateOfPaiement, accountNumber, total)
        } else {
            val intent = Intent(Settings.ACTION_NFC_SETTINGS)
            context.startActivity(intent)
            startNfcScanner(context, nfcAdapter, stateOfPaiement, accountNumber, total)
        }
    }

    fun startNfcScanner(context: Context, nfcAdapter: NfcAdapter, stateOfPaiement: MutableState<StateOfPaiement>, accountNumber: MutableState<String>, total: String) {
        val nfcCallback = NFCCallback(context = context, nfcAdapter, stateOfPaiement, total, accountNumber)
        nfcAdapter.enableReaderMode(
            context as Activity?,
            nfcCallback,
            NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
            null
        )
    }

    fun stopNfcScanner(context: Context, nfcAdapter: NfcAdapter) {
        nfcAdapter.disableReaderMode(context as Activity?)
    }

    fun getTokenFromPreferences(): String? {
        return sharedPreferences.getString("token", "")
    }
}