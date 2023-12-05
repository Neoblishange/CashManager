package com.example.cashmanagerfront.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cashmanagerfront.ui.screens.TotalPayoutScreen
import com.example.cashmanagerfront.ui.screens.WelcomeScreen

@Composable
fun CashManagerNavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.TOTAL_PAYOUT_SCREEN) {

        composable(Routes.WELCOME_SCREEN) {
            WelcomeScreen(navController)
        }

        composable(Routes.TOTAL_PAYOUT_SCREEN) {
            TotalPayoutScreen(navController)
        }
    }
}