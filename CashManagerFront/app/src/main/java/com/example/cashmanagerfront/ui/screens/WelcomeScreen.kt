package com.example.cashmanagerfront.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
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
    WelcomeScreen()
}