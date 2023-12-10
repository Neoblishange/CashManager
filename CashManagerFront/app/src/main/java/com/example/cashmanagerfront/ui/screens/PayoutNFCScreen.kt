package com.example.cashmanagerfront.ui.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.nfc.tech.MifareClassic
import android.nfc.tech.Ndef
import android.nfc.tech.NdefFormatable
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cashmanagerfront.ui.screens.widgets.AppBarWidget
import com.example.cashmanagerfront.ui.screens.widgets.BackgroundApp
import com.example.cashmanagerfront.ui.screens.widgets.CustomText
import com.example.cashmanagerfront.ui.utils.Strings
import android.provider.Settings
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun PayoutNFCScreen(navController: NavHostController, total: String = "90") {
    val context: Context = LocalContext.current
    val nfcAdapter = NfcAdapter.getDefaultAdapter(context)

    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        LaunchedEffect(key1 = true) {
            isNfcAvailable(context, nfcAdapter)
        }
        BackgroundApp()
        AppBarWidget(
            navController = navController,
            title = Strings.APP_BAR_PAYOUT_CARD,
            showBackArrow = true,
            showIcon = false
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            CustomText(
                text = Strings.TOTAL_PAYOUT_BODY, size = 26.sp, color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))

            CustomText(
                text = total + " " + Strings.TOTAL_PAYOUT_CURRENCY,
                color = Color.White,
                size = 40.sp
            )
            TextButton(onClick = {
                isNfcAvailable(context, nfcAdapter)
                val yourTagCallback = YourTagCallback()
                nfcAdapter?.enableReaderMode(context as Activity?, yourTagCallback, NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK, null)
            }) {
                Text(text = "start nfc scan", color = Color.White)
            }
            TextButton(onClick = {
                nfcAdapter?.disableReaderMode(context as Activity?)
            }) {
                Text(text = "stop nfc scan", color = Color.White)
            }
            Spacer(modifier = Modifier.weight(1f))
        }

    }
}

fun isNfcAvailable(context: Context, nfcAdapter: NfcAdapter) {
    if (nfcAdapter != null && nfcAdapter.isEnabled) {
        println("if")
    } else {
        println("else")
        val intent = Intent(Settings.ACTION_NFC_SETTINGS)
        context.startActivity(intent)
    }
}

class YourTagCallback : NfcAdapter.ReaderCallback {
    override fun onTagDiscovered(tag: Tag?) {
        println("onTagDiscovered")
        tag?.let {
            val mifareClassic = MifareClassic.get(it)
            println("mifare ------> ${mifareClassic }")

            mifareClassic?.let { mifare ->
                mifare.connect()

                val sectorCount = mifare.sectorCount
                println("sectorCount ------> $sectorCount")

                println("mifare tag -------> ${mifare.tag} ")
                println("mifare sectorCount -------> ${mifare.sectorCount} ")
                println("mifare size -------> ${mifare.size} ")
                println("mifare isConnected -------> ${mifare.isConnected} ")
                println("mifare blockCount -------> ${mifare.blockCount} ")
                for (sectorIndex in 0 until sectorCount) {
                    if (mifare.authenticateSectorWithKeyA(sectorIndex, MifareClassic.KEY_DEFAULT)) {
                        val blockCount = mifare.getBlockCountInSector(sectorIndex)
                        val startBlock = mifare.sectorToBlock(sectorIndex)
                        val endBlock = startBlock + blockCount

                        for (blockIndex in startBlock until endBlock) {
                            val data = mifare.readBlock(blockIndex)
                            val bytesData = String(data)
                            println("bytesData ------> $bytesData")
                            println("Données lues depuis le bloc $blockIndex : ${data.contentToString()}")
                        }
                    }
                }
                mifare.close()
            }
        }

    }
}