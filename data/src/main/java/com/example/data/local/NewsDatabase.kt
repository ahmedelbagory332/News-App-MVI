package com.example.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.entities.ArticleEntity
import com.example.data.local.entities.FavArticleEntity


@Database(entities = [ArticleEntity::class,FavArticleEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    abstract fun favNewsDao(): FavNewsDao
}