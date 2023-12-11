package com.example.cashmanagerfront.ui.screens

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.cashmanagerfront.domain.usecase.model.Article

@SuppressLint("UnrememberedMutableState")
@Composable
fun ShopPage1(navController: NavController) {

    val articlesList = remember { mutableStateOf(
        listOf(
            Article("Farine", 19.99),
            Article("Confiture", 29.99),
            Article("Sucre", 14.99),
            Article("Fraises", 16.99),
            Article("Sel", 4.99),
            Article("Lait", 34.99),
            Article("Levure", 24.99)
        )
    ) }

    Surface(modifier = Modifier.fillMaxSize()) {


        Column {
            TitleComponent(
                welcomeText = "Welcome Sofiane!,",
                selectArticlesText = "Voici notre selection d'article :"
            )
            LazyColumn {
                items(articlesList.value.size) { index ->
                    ArticleItem(
                        article = articlesList.value[index],
                        onAddClick = {
                            articlesList.value[index].quantity++
                        },
                        onRemoveClick = {
                            if (articlesList.value[index].quantity > 0) {
                                articlesList.value[index].quantity--
                            }
                        }
                    )
                }
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


@Composable
fun ArticleItem(article: Article, onAddClick: () -> Unit, onRemoveClick: () -> Unit) {
    var curretQuantity = remember {
        mutableStateOf(article.quantity)
    }
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
                article.quantity -= 1
                println(article.quantity)

            }) {

                Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove")
            }
            println(article.quantity)
            Text(text = "${curretQuantity}")
            IconButton(onClick = {
               article.quantity += 1
                println(article.quantity)


            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    }


}


