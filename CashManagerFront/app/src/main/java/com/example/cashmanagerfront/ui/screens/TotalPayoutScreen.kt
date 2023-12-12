package com.example.cashmanagerfront.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cashmanagerfront.R
import com.example.cashmanagerfront.ui.navigation.Routes
import com.example.cashmanagerfront.ui.screens.widgets.AppBarWidget
import com.example.cashmanagerfront.ui.screens.widgets.BackgroundApp
import com.example.cashmanagerfront.ui.screens.widgets.CustomText
import com.example.cashmanagerfront.ui.utils.Strings

@Composable
fun TotalPayoutScreen(navController: NavHostController, total: String = "90") {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BackgroundApp()
        AppBarWidget(
            navController = navController,
            title = Strings.APP_BAR_TOTAL,
            showBackArrow = false,
            showIcon = false
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(15.dp),
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

            Spacer(modifier = Modifier.height(130.dp))
            CustomText(
                text = Strings.TOTAL_PAYOUT_CHOICE_SYSTEM, color = Color.White, size = 26.sp
            )
            Spacer(modifier = Modifier.height(50.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                        .background(color = Color.White)
                        .height(150.dp)
                        .width(150.dp)
                        .clickable {
                            navController.navigate(Routes.PAYOUT_NFC_SCREEN)
                        }
                ) {
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
                        Icon(
                            painter = painterResource(id = R.drawable.credit_card_fill0_wght400_grad0_opsz24),
                            contentDescription = "card",
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                        )
                        CustomText(text = Strings.TOTAL_PAYOUT_CHOICE_SYSTEM_CARD)
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                        .background(color = Color.White)
                        .height(150.dp)
                        .width(150.dp)
                        .clickable {
                            navController.navigate(Routes.PAYOUT_QR_SCREEN)
                        }
                ) {
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
                        Icon(
                            painter = painterResource(id = R.drawable.qr_code_scanner_fill0_wght400_grad0_opsz24),
                            contentDescription = "qr",
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp)
                        )
                        CustomText(text = Strings.TOTAL_PAYOUT_CHOICE_SYSTEM_QR)
                    }

                }
                Spacer(modifier = Modifier.weight(1f))

            }
            Spacer(modifier = Modifier.weight(1f))
        }

    }
}

@Preview
@Composable
fun TotalPayoutScreenPreview() {
    TotalPayoutScreen(rememberNavController())
}