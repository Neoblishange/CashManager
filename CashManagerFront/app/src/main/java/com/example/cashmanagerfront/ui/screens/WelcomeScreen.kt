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
import com.example.cashmanagerfront.ui.navigation.Routes

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navController.navigate(Routes.TOTAL_PAYOUT_SCREEN)
            }
    ){
        Text(
            modifier = Modifier
                .fillMaxSize()
                .height(80.dp),
            text = Routes.WELCOME_SCREEN
        )
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(rememberNavController())
}