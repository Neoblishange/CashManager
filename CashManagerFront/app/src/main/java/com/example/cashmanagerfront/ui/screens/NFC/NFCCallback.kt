package com.example.cashmanagerfront.ui.screens.NFC

import android.content.Context
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.MifareClassic
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ReadOnlyComposable
import com.example.cashmanagerfront.ui.utils.Strings
import org.json.JSONArray
import org.json.JSONObject

enum class StateOfPaiement {
    INITIAL_NFC,
    INITIAL_QR,
    PENDING,
    PENDING_DATA,
    ACCEPTED,
    REFUSED,
    QR_ERROR,
    QR_CORRECT,
    QR_WRONG;

    val state: String
        @Composable
        @ReadOnlyComposable
        get() = when(this) {
            INITIAL_NFC -> Strings.NFC_INITIAL
            INITIAL_QR -> Strings.QR_INITIAL
            PENDING -> Strings.NFC_PENDING
            PENDING_DATA -> Strings.NFC_PENDING_DATA
            ACCEPTED -> Strings.NFC_ACCEPTED
            REFUSED -> Strings.NFC_REFUSED
            QR_ERROR -> Strings.QR_ERROR
            QR_CORRECT -> Strings.QR_CORRECT
            QR_WRONG -> Strings.QR_WRONG
        }
}

class NFCCallback(private var context: Context, private var nfcAdapter: NfcAdapter, private val stateOfPaiement: MutableState<StateOfPaiement>, private var total: String, private var numberAccount: MutableState<String>) : NfcAdapter.ReaderCallback {
    val viewModel: TransactionViewModel = TransactionViewModel(context)
    override fun onTagDiscovered(tag: Tag?) {
        tag?.let {
            val mifareClassic = MifareClassic.get(it)
            val jsonArray = JSONArray()
            stateOfPaiement.value = StateOfPaiement.PENDING_DATA

            mifareClassic?.let { mifare ->
                mifare.connect()

                val sectorCount = mifare.sectorCount
                for (sectorIndex in 0 until sectorCount) {
                    if (mifare.authenticateSectorWithKeyA(sectorIndex, MifareClassic.KEY_DEFAULT)) {
                        val blockCount = mifare.getBlockCountInSector(sectorIndex)
                        val startBlock = mifare.sectorToBlock(sectorIndex)
                        val endBlock = startBlock + blockCount

                        for (blockIndex in startBlock until endBlock) {
                            val data = String(mifare.readBlock(blockIndex))
                            val cleanedData = cleanString(data)
                            val jsonObject = JSONObject()
                            jsonObject.put("data", cleanedData)

                            jsonArray.put(jsonObject)
                        }
                    }
                }

                cleanJSONArray(jsonArray)
                val account = getAccountNumberFromJsonArray(jsonArray)
                stateOfPaiement.value = StateOfPaiement.PENDING
                viewModel.postPayout(stateOfPaiement, total, account, context)
                mifare.close()
                viewModel.stopNfcScanner(context = context, nfcAdapter)
            }
        }

    }
}

private fun cleanString(data: String): String {
    val excludedChars =
        "���\u0019�\b\u0004??\u0002\u0010�\u0007P\u001F�\u001D\u0000\u0003\u0001i\n\\"
    val stringBuilder = StringBuilder()

    for (char in data) {
        if (char !in excludedChars) {
            stringBuilder.append(char)
        }
    }

    return stringBuilder.toString()
}

private fun cleanJSONArray(jsonArray: JSONArray) {
    var i = 0
    while (i < jsonArray.length()) {
        val obj = jsonArray.getJSONObject(i)
        val dataValue = obj.optString("data")
        if (dataValue.isNullOrEmpty()) {
            jsonArray.remove(i)
            i--
        }
        i++
    }
}

private fun getAccountNumberFromJsonArray(jsonArray: JSONArray): String {
    val stringBuilder = StringBuilder()
    val regex = Regex("[0-9]")

    for (i in 0 until jsonArray.length()) {
        val obj = jsonArray.getJSONObject(i)
        val dataValue = obj.optString("data")

        if (regex.containsMatchIn(dataValue)) {
            stringBuilder.append(dataValue)
        }
    }
    val account = stringBuilder.toString().split('"')

    return account[1]
}