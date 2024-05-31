package com.example.domain.repositories

import com.example.domain.model.ArticlesModel
import com.example.domain.model.FavArticleModel
import kotlinx.coroutines.flow.Flow

interface CachedNewsRepository {

    suspend fun getAllCachedNews(): Flow<List<ArticlesModel>>

    suspend fun cacheNews(news: List<ArticlesModel>)

    suspend fun getAllFavCachedNews(): Flow<List<FavArticleModel>>

    suspend fun saveFavNews(news: ArticlesModel)

    suspend fun deleteFavNews(title: String)


}