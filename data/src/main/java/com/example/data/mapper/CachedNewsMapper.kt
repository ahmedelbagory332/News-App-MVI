package com.example.data.mapper

import com.example.data.local.entities.ArticleEntity
import com.example.data.local.entities.FavArticleEntity
import com.example.domain.model.ArticlesModel
import com.example.domain.model.FavArticleModel

fun ArticleEntity.toDomain(): ArticlesModel =
    ArticlesModel(
        id = id,
        sourceName = source_newspaper,
        title = title,
        description = Short_description,
        url = url,
        urlToImage = image,
        publishedAt = date
    )

fun ArticlesModel.toDomain(): ArticleEntity =
    ArticleEntity(
        id = id,
        source_newspaper = sourceName,
        title = title,
        Short_description = description,
        url = url,
        image = urlToImage,
        date = publishedAt
    )

fun FavArticleEntity.toDomain(): FavArticleModel =
    FavArticleModel(
        id = id,
        sourceName = source_newspaper,
        title = title,
        description = Short_description,
        url = url,
        urlToImage = image,
        publishedAt = date
    )

fun FavArticleModel.toDomain(): FavArticleEntity =
    FavArticleEntity(
        id = id,
        source_newspaper = sourceName,
        title = title,
        Short_description = description,
        url = url,
        image = urlToImage,
        date = publishedAt
    )


