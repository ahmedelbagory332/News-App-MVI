package com.example.presentation.screens.search

import com.example.domain.model.ArticlesModel

sealed class SearchIntent {
    object LoadOnBoardingStatus : SearchIntent()
    data class LoadSearchedArticles(val country: String, val category: String,val search: String) : SearchIntent()
    object LoadFavArticles : SearchIntent()
    data class SaveFavArticle(val article: ArticlesModel) : SearchIntent()
    data class DeleteFavArticle(val title: String) : SearchIntent()
    data class SetSelectedCategory(val category: String) : SearchIntent()
    data class ChangeQuerySearch(val query: String) : SearchIntent()
    data class ChangeShowPlaceHolder(val value: Boolean) : SearchIntent()

}
