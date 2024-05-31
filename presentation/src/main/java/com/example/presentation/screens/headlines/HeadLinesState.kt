package com.example.presentation.screens.headlines

import com.example.domain.model.ArticlesModel
import com.example.domain.model.OnBoardingModel
import com.example.presentation.screens.favorites.FavoritesArticlesState


data class HeadLinesState(
    val isLoading: Boolean = false,
    val headLines: List<ArticlesModel> = emptyList(),
    val favoritesArticlesState: FavoritesArticlesState = FavoritesArticlesState(),
    val error: String = "",
    val selectedCategory: String = "",
    val languagePreference: String = "",
    val onBoardingStatus: OnBoardingModel = OnBoardingModel()
)
