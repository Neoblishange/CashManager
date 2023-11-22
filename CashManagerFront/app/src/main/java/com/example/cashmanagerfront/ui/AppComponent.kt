package com.example.cashmanagerfront.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.cashmanagerfront.ui.utils.Strings

@Composable
fun TopBar() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = Strings.APP_BAR_TOTAL,
            color = Color.Magenta,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}