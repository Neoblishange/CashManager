package com.example.cashmanagerfront.ui.screens.widgets

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun CustomText(text: String, color: Color = DARK_BLUE, size: TextUnit = 22.sp, fontWeight: FontWeight = FontWeight.Normal, textAlign: TextAlign = TextAlign.Center, modifier: Modifier = Modifier) {
    val font = FontFamily(
        Font(R.font.mermaid1001, FontWeight.Normal)
    )

    Text(
        text = text,
        color = color,
        fontFamily = font,
        fontSize = size,
        fontWeight = fontWeight,
        textAlign = textAlign,
        modifier = modifier
    )
}