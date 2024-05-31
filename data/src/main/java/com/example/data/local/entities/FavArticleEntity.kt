package com.example.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "fav_news")
data class FavArticleEntity(
    @PrimaryKey val id: Long?,
    val title: String?,
    val date: String?,
    val image: String?,
    val url: String?,
    val source_newspaper: String?,
    val Short_description: String?,
)


