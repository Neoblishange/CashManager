package com.example.cashmanagerfront.ui.screens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cashmanagerfront.R
import com.example.cashmanagerfront.ui.navigation.Routes
import com.example.cashmanagerfront.ui.screens.widgets.BackgroundApp
import com.example.cashmanagerfront.ui.screens.widgets.CustomText
import com.example.cashmanagerfront.ui.theme.DARK_GREEN_BUBBLE
import com.example.cashmanagerfront.ui.theme.GREEN_BUBBLE
import com.example.cashmanagerfront.ui.theme.LIGHT_GREEN_BUBBLE
import com.example.cashmanagerfront.ui.utils.Constant
import com.example.cashmanagerfront.ui.utils.Strings
import innerShadow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController) {
    var context: Context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)

    Surface(modifier = Modifier.fillMaxSize()) {
        val token = sharedPreferences.getString("token", "")
        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(key1 = true) {
            coroutineScope.launch {
                delay(3000)
                navController.navigate(Routes.SHOP_SCREEN) {
                    popUpTo(Routes.SPLASH_SCREEN) {
                        inclusive = true
                    }
                }
            }
            Constant.IS_AUTHENTICATED.value = token != null && token != ""

        }

        BackgroundApp()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .height(250.dp)
                    .width(250.dp)
                    .clip(shape = CircleShape)
                    .background(GREEN_BUBBLE)
                    .innerShadow(
                        color = LIGHT_GREEN_BUBBLE,
                        cornersRadius = 400.dp,
                        spread = 10.dp,
                        blur = 2.dp
                    )
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    androidx.compose.material3.Icon(
                        painter = painterResource(id = R.drawable.cost),
                        contentDescription = "logo"
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    CustomText(text = Strings.APP_TITLE, size = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
