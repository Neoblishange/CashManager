package com.example.cashmanagerfront.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cashmanagerfront.ui.screens.ShopPage1
import com.example.cashmanagerfront.ui.screens.PayoutNFCScreen
import com.example.cashmanagerfront.ui.screens.PayoutQRScreen
import com.example.cashmanagerfront.ui.screens.SplashScreen
import com.example.cashmanagerfront.ui.screens.TotalPayoutScreen
import com.example.cashmanagerfront.ui.screens.WelcomeScreen

@Composable
fun CashManagerNavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.SPLASH_SCREEN) {

        composable(Routes.WELCOME_SCREEN) {
            WelcomeScreen(navController)
        }

        composable(Routes.SHOP_SCREEN) {
            ShopPage1(navController)
        }

        composable(Routes.SPLASH_SCREEN) {
            SplashScreen(navController)
        }

        composable(Routes.TOTAL_PAYOUT_SCREEN) { backStackEntry ->
            val total = backStackEntry.arguments?.getString("total") ?: "0"
            TotalPayoutScreen(navController, total)
        }

        composable(Routes.PAYOUT_NFC_SCREEN) {
            PayoutNFCScreen(navController)
        }

        composable(Routes.PAYOUT_QR_SCREEN) {
            PayoutQRScreen(navController)
        }
    }
}