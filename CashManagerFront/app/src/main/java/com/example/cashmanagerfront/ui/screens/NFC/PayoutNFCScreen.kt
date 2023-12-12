package com.example.cashmanagerfront.ui.screens.NFC

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun PayoutNFCScreen(navController: NavHostController, total: String) {
    val context: Context = LocalContext.current
    val nfcAdapter = NfcAdapter.getDefaultAdapter(context)
    val stateOfPaiement = remember {
        mutableStateOf(StateOfPaiement.INITIAL)
    }

    val accountNumber: MutableState<String> = remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        LaunchedEffect(key1 = true) {
            isNfcAvailable(context, nfcAdapter, stateOfPaiement, accountNumber)
        }
        DisposableEffect(key1 = nfcAdapter) {
            onDispose {
                stopNfcScanner(context, nfcAdapter)
            }
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
            Spacer(modifier = Modifier.height(50.dp))

            CustomText(text = stateOfPaiement.value.state, color = Color.White)

            if (stateOfPaiement.value.state == StateOfPaiement.PENDING.state) {
                CustomText(text = accountNumber.value, color = Color.White)
            }

            Spacer(modifier = Modifier.weight(1f))
        }

    }
}

fun isNfcAvailable(context: Context, nfcAdapter: NfcAdapter, stateOfPaiement: MutableState<StateOfPaiement>, accountNumber: MutableState<String>) {
    if (nfcAdapter.isEnabled) {
        startNfcScanner(context, nfcAdapter, stateOfPaiement, accountNumber)
    } else {
        val intent = Intent(Settings.ACTION_NFC_SETTINGS)
        context.startActivity(intent)
    }
}

fun startNfcScanner(context: Context, nfcAdapter: NfcAdapter, stateOfPaiement: MutableState<StateOfPaiement>, accountNumber: MutableState<String>) {
    val nfcCallback = NFCCallback(stateOfPaiement, accountNumber)
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
