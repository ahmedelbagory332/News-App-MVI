package com.example.data.mapper

import com.example.data.remote.responses.ArticlesJson
import com.example.data.remote.responses.HeadLinesJson
import com.example.domain.model.ArticlesModel
import com.example.domain.model.NewsModel

fun HeadLinesJson.toDomain(): NewsModel =
    NewsModel(
        articles = articles.map { it.toDomain() },
    )


fun ArticlesJson.toDomain(): ArticlesModel =
    ArticlesModel(
        id = null,
        sourceName = source.name,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt
    )

