package com.example.presentation.screens.favorites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.screens.favorites.component.FavItemArticle
import com.example.presentation.theme.NewsAppTheme
import com.example.presentation.theme.darkWhite
import com.example.presentation.theme.grey
import com.example.presentation.utils.ErrorHolder
import com.example.presentation.utils.LoadingIndicator
import com.example.presentation.utils.TopBar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoritesActivity : ComponentActivity() {
    private val viewModel: FavoritesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = grey
                ) {
                    FavoritesScreen()
                }
            }
        }
    }


    @Composable
    fun FavoritesScreen() {
        Column(
            modifier = Modifier
                .background(darkWhite)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Surface(shadowElevation = 3.dp) {
                TopBar(
                    title = stringResource(R.string.favorites_articles),
                    menu = {
                    }
                )
            }

            FavoritesArticles(viewModel)

        }


    }

    @Composable
    fun FavoritesArticles(headLinesViewModel: FavoritesViewModel) {
        if (headLinesViewModel.favState.value.isLoading) {
            LoadingIndicator()
        } else if (headLinesViewModel.favState.value.articles.isNotEmpty()) {

            LazyColumn {
                items(headLinesViewModel.favState.value.articles) { article ->
                    FavItemArticle(article,headLinesViewModel)
                }
            }
        } else if (headLinesViewModel.favState.value.error.isNotEmpty()) {
            ErrorHolder(text = headLinesViewModel.favState.value.error)
        }else if (headLinesViewModel.favState.value.articles.isEmpty()) {

            Column(
                modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.no_result),
                    contentDescription = "no_result",
                    modifier = Modifier.size(width = 250.dp, height = 250.dp)
                )
            }
        }

    }


}


 
