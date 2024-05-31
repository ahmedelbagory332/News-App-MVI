package com.example.presentation.screens.favorites

sealed class FavoritesIntent {
    object LoadFavArticles : FavoritesIntent()
    data class DeleteFavArticle(val title: String) : FavoritesIntent()
}
