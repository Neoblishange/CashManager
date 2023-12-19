package com.example.cashmanagerfront.ui.screens.WelcomeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.cashmanagerfront.ui.navigation.Routes
import com.example.cashmanagerfront.ui.screens.widgets.BackgroundApp
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(navController: NavHostController) {
    val viewModel = WelcomeScreenViewModel()
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navController.navigate(Routes.TOTAL_PAYOUT_SCREEN)
            }
    ){
        BackgroundApp()
        Column {
            TextField(value = viewModel.name.value, onValueChange = {value ->
                viewModel.name.value = value
            })
            TextField(value = viewModel.password.value, onValueChange = {value ->
                viewModel.password.value = value
            })
            Text(text = "Name : ${viewModel.name.value}", color = Color.White)
            Text(text = "Password : ${viewModel.password.value}", color = Color.White)
            TextButton(onClick = {
                coroutineScope.launch {
                    println(viewModel.password.value + " "+ viewModel.name.value)
                    val response = viewModel.createUser()
//                    if (response != null) {
//                        navController.navigate(Routes.TOTAL_PAYOUT_SCREEN)
//                    }
                }
            }) {
                Text(text = "Valider")
            }
        }
    }
}