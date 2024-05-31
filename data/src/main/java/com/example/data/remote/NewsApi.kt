package com.example.data.remote

import com.example.data.Constant
import com.example.data.remote.responses.HeadLinesJson
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines?apiKey=${Constant.ApiKey}")
    suspend fun getHeadLines(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("q") search: String="",
    ): HeadLinesJson
}