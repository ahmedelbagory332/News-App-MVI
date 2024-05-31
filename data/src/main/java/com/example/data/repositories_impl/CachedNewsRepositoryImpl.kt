package com.example.data.repositories_impl

import androidx.room.withTransaction
import com.example.data.local.NewsDatabase
import com.example.data.mapper.toDomain
import com.example.domain.model.ArticlesModel
import com.example.domain.model.FavArticleModel
import com.example.domain.repositories.CachedNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CachedNewsRepositoryImpl @Inject constructor(
    private val db: NewsDatabase
) : CachedNewsRepository {

    override suspend fun getAllCachedNews(): Flow<List<ArticlesModel>> {
        return db.newsDao().getAllNews().map { it -> it.map { it.toDomain() } }
    }

    override suspend fun cacheNews(news: List<ArticlesModel>) {
        db.withTransaction {
            db.newsDao().deleteAllNews()
            db.newsDao().insertNews(news.map { it.toDomain() })
        }
    }

    override suspend fun getAllFavCachedNews(): Flow<List<FavArticleModel>> {
        return db.favNewsDao().getAllFavNews().map { it -> it.map { it.toDomain() } }
    }


    override suspend fun saveFavNews(news: ArticlesModel) {
         db.favNewsDao().insertFavNews(news.toDomain())
    }

    override suspend fun deleteFavNews(title: String) {
        db.favNewsDao().deleteByTitle(title)

    }


}