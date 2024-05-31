package com.example.domain.repositories

import com.example.domain.model.NewsModel


interface GetHeadLinesRepository {

    suspend fun getRemoteHeadLines(country: String, category: String,search: String=""): NewsModel


}