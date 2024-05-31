package com.example.data.repositories_impl

import com.example.data.mapper.toDomain
import com.example.data.remote.NewsApi
import com.example.domain.model.NewsModel
import com.example.domain.repositories.GetHeadLinesRepository
import javax.inject.Inject


class GetHeadLinesRepositoryImpl @Inject constructor(private val api: NewsApi) :
    GetHeadLinesRepository {


    override suspend fun getRemoteHeadLines(country: String, category: String,search: String): NewsModel {
        return api.getHeadLines(country, category,search).toDomain()
    }
}