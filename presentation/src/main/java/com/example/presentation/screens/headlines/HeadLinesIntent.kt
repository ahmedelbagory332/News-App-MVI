package com.example.presentation.screens.headlines

import com.example.domain.model.ArticlesModel

sealed class HeadLinesIntent {
    object LoadOnBoardingStatus : HeadLinesIntent()
    data class LoadHeadLines(val country: String, val category: String) : HeadLinesIntent()
    object LoadFavArticles : HeadLinesIntent()
    data class SaveFavArticle(val article: ArticlesModel) : HeadLinesIntent()
    data class DeleteFavArticle(val title: String) : HeadLinesIntent()
    data class SetSelectedCategory(val category: String) : HeadLinesIntent()
    data class SetLanguagePreference(val language: String) : HeadLinesIntent()
}
