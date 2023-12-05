package com.example.cashmanagerfront.ui.screens

import android.graphics.drawable.Icon
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cashmanagerfront.R
import com.example.cashmanagerfront.ui.navigation.Routes
import com.example.cashmanagerfront.ui.screens.widgets.AppBarWidget
import com.example.cashmanagerfront.ui.utils.Strings


@Composable
fun TotalPayoutScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AppBarWidget(
            navController = navController,
            title = Strings.APP_BAR_TOTAL,
            showIcon = false
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = Strings.TOTAL_PAYOUT_BODY
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "30 " + Strings.TOTAL_PAYOUT_CURRENCY)
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = Strings.TOTAL_PAYOUT_CHOICE_SYSTEM
                )
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .background(color = Color.Magenta)
                            .height(100.dp)
                            .width(100.dp)
                            .clickable {
                                navController.navigate(Routes.WELCOME_SCREEN)
                            }
                    ) {
                        androidx.compose.material3.Icon(painter = painterResource(id = R.drawable.credit_card_fill0_wght400_grad0_opsz24), contentDescription = "crédit_card" )
                    }
                    Spacer(modifier = Modifier.width(50.dp))
                    Box(
                        modifier = Modifier
                            .background(color = Color.Green)
                            .height(100.dp)
                            .width(100.dp)
                            .clickable {
                                navController.navigate(Routes.WELCOME_SCREEN)
                            }
                    ) {
                        androidx.compose.material3.Icon(painter = painterResource(id = R.drawable.qr_code_scanner_fill0_wght400_grad0_opsz24), contentDescription = "qr" )

                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun TotalPayoutScreenPreview() {
    TotalPayoutScreen(rememberNavController())
}