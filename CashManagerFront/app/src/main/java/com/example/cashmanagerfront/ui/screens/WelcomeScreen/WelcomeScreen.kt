package com.example.cashmanagerfront.ui.screens.WelcomeScreen

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.cashmanagerfront.ui.navigation.Routes
import com.example.cashmanagerfront.ui.screens.widgets.BackgroundApp

@Composable
fun WelcomeScreen(navController: NavHostController) {
    val viewModel = WelcomeScreenViewModel()
    val context: Context = LocalContext.current


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
                    viewModel.createUser(context = context)
            }) {
                Text(text = "Valider")
            }
            Text(text = viewModel.message.value, color = Color.White)

        }
    }
}