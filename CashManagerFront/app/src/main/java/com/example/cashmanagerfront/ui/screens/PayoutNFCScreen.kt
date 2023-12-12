package com.example.cashmanagerfront.ui.screens

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


@Composable
fun PayoutNFCScreen(navController: NavHostController, total: String = "90") {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
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

            Spacer(modifier = Modifier.weight(1f))
        }

    }
}
