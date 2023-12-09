package com.example.cashmanagerfront.ui.screens.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cashmanagerfront.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarWidget(navController: NavHostController, title: String, showBackArrow: Boolean, showIcon: Boolean) {

    Box(modifier = Modifier
        .fillMaxSize()
        ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            if (showBackArrow)
                androidx.compose.material3.Icon(painter = painterResource(id = R.drawable.arrowleft1_1), contentDescription = "back" )
            CustomText(text = title, color = Color.White)
            if (showIcon)
                androidx.compose.material3.Icon(painter = painterResource(id = R.drawable.baseline_settings_24), contentDescription = "settings" )
        }
    }
}