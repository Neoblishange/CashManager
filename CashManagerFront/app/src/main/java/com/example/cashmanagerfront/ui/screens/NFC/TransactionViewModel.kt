package com.example.cashmanagerfront.ui.screens.NFC

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.provider.Settings
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.example.cashmanagerfront.data.ApiService
import com.example.cashmanagerfront.domain.usecase.model.Data
import com.example.cashmanagerfront.domain.usecase.model.Payout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionViewModel: ViewModel() {

    fun postPayout(stateOfPaiement: MutableState<StateOfPaiement>, total: String, accountNumber: String) {
        stateOfPaiement.value = StateOfPaiement.PENDING

        var payout = Payout(accountNumber = accountNumber, amount = total.toFloat())

        val call: Call<Data?>? = ApiService.transactionRepository.payout(payout)

        call!!.enqueue(object : Callback<Data?> {
            override fun onResponse(call: Call<Data?>?, response: Response<Data?>?) {
                if(response!!.isSuccessful) {
                    stateOfPaiement.value = StateOfPaiement.ACCEPTED
                } else {
                    stateOfPaiement.value = StateOfPaiement.REFUSED
                }
            }

            override fun onFailure(call: Call<Data?>?, t: Throwable) {
                stateOfPaiement.value = StateOfPaiement.REFUSED
            }
        })
    }

    fun checkIfAmountIsEqualsToTotalQR(stateOfPaiement: MutableState<StateOfPaiement>, total: String, amountQr: String, accountNumber: String) {
        stateOfPaiement.value = StateOfPaiement.PENDING_DATA
        if(total.toFloat() == amountQr.toFloat()) {
            stateOfPaiement.value = StateOfPaiement.QR_CORRECT
            postPayout(stateOfPaiement, total = total, accountNumber)
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
}