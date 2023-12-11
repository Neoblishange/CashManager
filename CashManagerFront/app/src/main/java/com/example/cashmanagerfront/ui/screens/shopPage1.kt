package com.example.cashmanagerfront.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cashmanagerfront.domain.usecase.model.Article

@Composable
fun ShopPage1(navController: NavController) {

    val articlesList = remember { mutableStateListOf(
        Article("Farine", 19.99),
        Article("Confiture", 29.99),
        Article("Sucre", 14.99),
        Article("Fraises", 16.99),
        Article("Sel", 4.99),
        Article("Lait", 34.99),
        Article("Levure", 24.99)
    ) }

    Surface(modifier = Modifier.fillMaxSize()) {

        Column(  modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
        )  {
            TitleComponent(
                welcomeText = "Welcome Sofiane!,",
                selectArticlesText = "Voici notre selection d'article :"
            )
                for ( article in articlesList) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(16.dp),

                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = article.name, style = MaterialTheme.typography.body1)
                        Text(text = "${article.price}" + "€", style = MaterialTheme.typography.caption)

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            IconButton(onClick = {
                                    if (article.quantity.value > 0)
                                        article.quantity.value -= 1

                            }) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove")
                            }

                            Text(text = "${article.quantity.value}")

                            IconButton(onClick = {
                                    if (article.quantity.value <50)
                                        article.quantity.value += 1


                            }) {
                                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                            }
                        }

                    }
                }

            // Ajout du bouton en bas
            Button(
                onClick = {
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp) ,

            ) {
                Text("Valider")
            }

        }
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
            .fillMaxWidth()
            .heightIn(100.dp)
            .background(Color.White)
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = welcomeText,
            style = MaterialTheme.typography.h4.copy(fontSize = welcomeTextSize)

        )
        Text(
            text = selectArticlesText,
            style = MaterialTheme.typography.h4.copy(fontSize = SelectArticlesTextSize)
        )
    }

}

