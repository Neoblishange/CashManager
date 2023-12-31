package com.example.cashmanagerfront.ui.screens.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cashmanagerfront.R
import com.example.cashmanagerfront.ui.navigation.Routes
import com.example.cashmanagerfront.ui.theme.DARK_BLUE
import com.example.cashmanagerfront.ui.theme.Purple200

@Composable
fun AppBarWidget(
    navController: NavHostController,
    title: String,
    showBackArrow: Boolean,
    showIcon: Boolean
    // onSettingsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
    ) {

        Spacer(modifier = Modifier.weight(1f))

        CustomText(
            text = title,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            painter = painterResource(id = R.drawable.baseline_settings_24),
            contentDescription = "settings",
            tint = if (showIcon) DARK_BLUE else Color.Transparent,
            modifier = Modifier
                .padding(end = 16.dp)
                .clickable(enabled = showIcon) {
                    navController.navigate(Routes.SETTINGS_SCREEN)
                }
        )
    }
}