package com.example.cashmanagerfront.ui.screens.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.cashmanagerfront.ui.theme.DARK_BLUE
import com.example.cashmanagerfront.ui.theme.DARK_GREEN_BUBBLE
import com.example.cashmanagerfront.ui.theme.GREEN_BUBBLE
import innerShadow

@Composable
fun BackgroundApp() {
    Surface {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DARK_BLUE)
        ) {
            Box(
                modifier = Modifier
                    .offset(y = (-250).dp, x = 70.dp)
                    .clip(shape = CircleShape)
                    .background(GREEN_BUBBLE)
                    .height(400.dp)
                    .width(400.dp)
                    .innerShadow(
                        cornersRadius = 400.dp,
                        spread = 10.dp,
                        offsetX = 2.dp,
                        offsetY = 2.dp,
                        blur = 5.dp,
                        color = DARK_GREEN_BUBBLE
                    )
            )
            Box(
                modifier = Modifier
                    .offset(y = 700.dp, x = (-50).dp)
                    .clip(shape = CircleShape)
                    .background(GREEN_BUBBLE)
                    .height(400.dp)
                    .width(400.dp)
                    .innerShadow(
                        cornersRadius = 400.dp,
                        spread = 10.dp,
                        offsetX = (-2).dp,
                        offsetY = (-2).dp,
                        blur = 5.dp,
                        color = DARK_GREEN_BUBBLE
                    )


            )
        }
    }
}


