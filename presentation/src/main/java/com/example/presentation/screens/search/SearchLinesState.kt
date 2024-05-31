package com.example.presentation.screens.search

import com.example.domain.model.ArticlesModel
import com.example.domain.model.OnBoardingModel
import com.example.presentation.screens.favorites.FavoritesArticlesState


data class SearchLinesState(
    val isLoading: Boolean = false,
    val showPlaceHolder: Boolean = true,
    val headLines: List<ArticlesModel> = emptyList(),
    val favoritesArticlesState: FavoritesArticlesState = FavoritesArticlesState(),
    val error: String = "",
    val query: String = "",
    val selectedCategory: String = "",
    val onBoardingStatus: OnBoardingModel = OnBoardingModel()
)
