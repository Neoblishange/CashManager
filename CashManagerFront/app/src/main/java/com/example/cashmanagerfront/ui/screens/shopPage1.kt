package com.example.cashmanagerfront.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.cashmanagerfront.domain.usecase.model.Article
import com.example.cashmanagerfront.ui.navigation.Routes
import com.example.cashmanagerfront.ui.screens.widgets.AppBarWidget
import com.example.cashmanagerfront.ui.screens.widgets.BackgroundApp
import com.example.cashmanagerfront.ui.utils.Strings
import java.util.Locale
import com.example.cashmanagerfront.ui.screens.widgets.CustomText
import com.example.cashmanagerfront.ui.theme.RED
import com.example.cashmanagerfront.ui.theme.DARK_BLUE
import androidx.compose.material.TextButton
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.example.cashmanagerfront.ui.utils.Constant
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShopPage1(navController: NavHostController) {
    val context: Context = LocalContext.current

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    val articlesList = remember { mutableStateListOf(
        Article("Farine", 1.25),
        Article("Confiture", 29.99),
        Article("Sucre", 14.99),
        Article("Fraises", 16.99),
        Article("Sel", 100.00),
        Article("Lait", 5.00),
        Article("Levure", 24.99)
    ) }

    fun getTotal(): Double {
        var total = 0.0
        for (article in articlesList){
            total +=  (article.quantity.value * article.price)

        }
        return total
    }

    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    },
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            BackgroundApp()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())

            ) {
                AppBarWidget(
                    navController = navController,
                    title = Strings.APP_BAR_SHOP,
                    showBackArrow = false,
                    showIcon = true
                )
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .padding(start = 40.dp, end = 40.dp, top = 10.dp, bottom = 40.dp) // margin
                        .clip(shape = RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))


                ) {

                    for (article in articlesList) {
                        Row(
                            modifier = Modifier
                                .width(500.dp)
                                .background(Color.White)
                                .padding(start = 5.dp, end = 5.dp),


                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CustomText(text = article.name, size = 18.sp)
                            CustomText(
                                text = "${article.price}" + "€", size = 18.sp, modifier = Modifier
                                    .weight(1f)
                                    .wrapContentWidth(Alignment.End)
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                IconButton(onClick = {
                                    if (article.quantity.value > 0)
                                        article.quantity.value -= 1

                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Remove",
                                        tint = RED
                                    )
                                }

                                CustomText(text = "${article.quantity.value}")

                                IconButton(onClick = {
                                    if (article.quantity.value < 50)
                                        article.quantity.value += 1


                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Add",
                                        tint = DARK_BLUE
                                    )
                                }
                            }

                        }
                    }
                }

                totalComponent(
                    total = String.format(Locale.US, "%.2f", getTotal()),
                )
                Spacer(modifier = Modifier.weight(2f))
                TextButton(
                    onClick = {
                        var total = getTotal().toString()
                        if (Constant.IS_AUTHENTICATED.value) {
                            if(getTotal() != 0.00) {
                                navController.navigate(
                                    Routes.TOTAL_PAYOUT_SCREEN
                                        .replace(
                                            "{total}",
                                            String.format(Locale.US, "%.2f", getTotal())
                                        )
                                )
                            } else {
                                Toast.makeText(context, Strings.ERROR_PRICE_NULL, Toast.LENGTH_LONG).show()
                            }
                        } else {
                            scope.launch {
                                val result = snackbarHostState
                                    .showSnackbar(
                                        message = Strings.UNAUTHENTICATED,
                                        actionLabel = Strings.UNAUTHENTICATED_BUTTON,
                                        duration = SnackbarDuration.Long
                                    )
                                when (result) {
                                    SnackbarResult.ActionPerformed -> {
                                        navController.navigate(Routes.SETTINGS_SCREEN)
                                    }

                                    SnackbarResult.Dismissed -> {
                                    }
                                }
                            }


                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp, top = 10.dp, bottom = 40.dp)


                ) {
                    CustomText(
                        text = "Valider",

                        )

                }


            }
        }
    }

}


@Composable
fun totalComponent(
    total: String,
) {
    Row(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp)
            .background(
                Color.White, shape = RoundedCornerShape(15.dp)
            )
            .padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)


    ) {
        CustomText(
            text = " Votre total est de : ", size = 18.sp

        )
        CustomText(
            text = total + " €",
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.End), size = 18.sp

        )
    }

}


@Composable
fun TitleComponent(
    welcomeText: String,
    selectArticlesText: String,
    welcomeTextSize: TextUnit = 20.sp,
    SelectArticlesTextSize: TextUnit = 16.sp
) {
    Column(
        modifier = Modifier
            .width(300.dp)
            .padding(top = 50.dp) // margin
            .padding(top = 16.dp) // padding

    ) {
        Text(
            text = welcomeText,
            style = MaterialTheme.typography.h4.copy(fontSize = welcomeTextSize),
            color = Color.White,
            modifier = Modifier
                .padding(bottom = 40.dp)
                .padding(start = 20.dp)


        )
        Text(
            text = selectArticlesText,
            style = MaterialTheme.typography.h4.copy(fontSize = SelectArticlesTextSize),
            color = Color.White,
            modifier = Modifier
                .padding(start =20.dp)


        )
    }

}

