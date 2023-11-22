package com.example.cashmanagerfront

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.cashmanagerfront.ui.screens.CashManagerNavigationGraph
import com.example.cashmanagerfront.ui.theme.CashManagerFrontTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CashManagerFrontTheme {
                CashManagerApp()
            }
        }
    }

    @Composable
    fun CashManagerApp() {
        CashManagerNavigationGraph()
    }
}
