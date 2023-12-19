package com.example.cashmanagerfront.ui.screens.WelcomeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cashmanagerfront.ui.navigation.Routes
import com.example.cashmanagerfront.ui.screens.widgets.BackgroundApp

@Composable
fun WelcomeScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navController.navigate(Routes.TOTAL_PAYOUT_SCREEN)
            }
    ){
        BackgroundApp()
        Column {
            TextField(value = "", onValueChange = {value ->

            })
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(rememberNavController())
}