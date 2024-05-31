package com.example.presentation.screens.headlines

import android.content.Intent
import android.os.Build
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.screens.favorites.FavoritesActivity
import com.example.presentation.screens.headlines.component.ItemArticle
import com.example.presentation.screens.headlines.component.UserFavCategories
import com.example.presentation.screens.onboardingScreen.countries
import com.example.presentation.screens.search.SearchActivity
import com.example.presentation.theme.NewsAppTheme
import com.example.presentation.theme.darkWhite
import com.example.presentation.theme.grey
import com.example.presentation.utils.ErrorHolder
import com.example.presentation.utils.LoadingIndicator
import com.example.presentation.utils.TopBar
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class HeadLinesActivity : ComponentActivity() {
    private val viewModel: HeadLinesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = grey
                ) {
                    HeadLinesScreen()
                }
            }
        }
    }



    @Composable
    fun HeadLinesScreen() {
        CompositionLocalProvider(LocalLayoutDirection provides if (viewModel.getLanguagePref() == "en") LayoutDirection.Ltr else LayoutDirection.Rtl) {
            Column(
                modifier = Modifier
                    .background(darkWhite)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Surface(shadowElevation = 3.dp) {
                    TopBar(
                        title = stringResource(R.string.head_lines),
                        menu = {
                            IconButton(
                                onClick = {
                                    if (viewModel.getLanguagePref() == "en") {
                                        setLocale("ar")
                                        viewModel.sendIntent(HeadLinesIntent.SetLanguagePreference("ar"))
                                    } else {
                                        setLocale("en")
                                        viewModel.sendIntent(HeadLinesIntent.SetLanguagePreference("en"))
                                    }
                                    recreate()
                                }) {
                                Image(
                                    painter = painterResource(id = R.drawable.translate_icon),
                                    contentDescription = "My Image",
                                    modifier = Modifier.size(width = 30.dp, height = 30.dp)
                                )
                            }

                            IconButton(
                                onClick = {
                                    startFavoritesActivity()
                                }) {
                                Icon(Icons.Filled.Favorite, "FavoriteBorder")
                            }

                            IconButton(onClick = {
                                startSearchActivity()
                            }) {
                                Icon(Icons.Filled.Search, "search")
                            }

                        }
                    )
                }

                UserFavCategories(
                    categories = viewModel.state.value.onBoardingStatus.categories.toList(),
                    selectedCategory = viewModel.state.value.selectedCategory,

                ) { category ->
                    viewModel.sendIntent(HeadLinesIntent.SetSelectedCategory(category))
                    countries[viewModel.state.value.onBoardingStatus.country]?.let {
                         viewModel.sendIntent(HeadLinesIntent.LoadHeadLines(it, category))
                    }
                }


                HeadLines(viewModel)

            }
        }
    }

    @Composable
    fun HeadLines(headLinesViewModel: HeadLinesViewModel) {

        if (headLinesViewModel.state.value.isLoading) {
            LoadingIndicator()
        } else if (headLinesViewModel.state.value.headLines.isNotEmpty()) {

            LazyColumn {
                items(headLinesViewModel.state.value.headLines) { article ->
                    ItemArticle(article, headLinesViewModel)
                }
            }
        } else if (headLinesViewModel.state.value.error.isNotEmpty()) {
            ErrorHolder(text = headLinesViewModel.state.value.error)
        }
        else if (headLinesViewModel.state.value.headLines.isEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
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


    private fun startFavoritesActivity() {
        startActivity(Intent(this, FavoritesActivity::class.java))
    }

    private fun startSearchActivity() {
        startActivity(Intent(this, SearchActivity::class.java))
    }

    private fun setLocale(lang: String) {
        val config = resources.configuration
        val locale = Locale(lang)
        Locale.setDefault(locale)
        config.setLocale(locale)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)

    }


    override fun onResume() {
        setLocale(viewModel.getLanguagePref())
        super.onResume()
    }
}


 
