package com.example.data.local


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entities.ArticleEntity
import com.example.data.local.entities.FavArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavNewsDao {

    @Query("SELECT * FROM fav_news")
    fun getAllFavNews(): Flow<List<FavArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE , entity = FavArticleEntity::class)
    suspend fun insertFavNews(news: ArticleEntity)

    @Query("DELETE FROM fav_news WHERE title = :title")
    fun deleteByTitle(title: String)
}