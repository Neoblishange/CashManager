package com.example.cashmanagerfront.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun TotalPayoutScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize().clickable {
            navController.navigate(Routes.WELCOME_SCREEN)
        }
    ) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .height(80.dp),
            text = Routes.TOTAL_PAYOUT_SCREEN
        )
    }
}

@Preview
@Composable
fun TotalPayoutScreenPreview() {
    TotalPayoutScreen(rememberNavController())
}