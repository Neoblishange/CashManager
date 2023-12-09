package com.example.cashmanagerfront.ui.screens.widgets

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.cashmanagerfront.R
import com.example.cashmanagerfront.ui.theme.DARK_BLUE

@Composable
fun CustomText(text: String, color: Color = DARK_BLUE, size: TextUnit = 22.sp, fontWeight: FontWeight = FontWeight.Bold) {
    val font = FontFamily(
        Font(R.font.stray_cloud, FontWeight.Normal)
    )

    Text(
        text = text,
        color = color,
        fontFamily = font,
        fontSize = size,
        fontWeight = fontWeight,
        textAlign = TextAlign.Center
    )
}