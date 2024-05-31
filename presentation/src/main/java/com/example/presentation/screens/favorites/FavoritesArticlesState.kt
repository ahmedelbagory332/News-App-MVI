package com.example.presentation.screens.favorites

import com.example.domain.model.FavArticleModel


data class FavoritesArticlesState (
    val isLoading: Boolean = false,
    val articles : List<FavArticleModel>  = emptyList(),
    val error: String = ""
)