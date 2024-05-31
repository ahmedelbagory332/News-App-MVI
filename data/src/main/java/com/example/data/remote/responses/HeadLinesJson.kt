package com.example.data.remote.responses

import com.google.gson.annotations.SerializedName


data class HeadLinesJson(

    @SerializedName("status") var status: String? = null,
    @SerializedName("totalResults") var totalResults: Int? = null,
    @SerializedName("articles") var articles: List<ArticlesJson> = listOf()

)

data class SourceJson(

    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null

)

data class ArticlesJson(

    @SerializedName("source") var source: SourceJson = SourceJson(),
    @SerializedName("author") var author: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("urlToImage") var urlToImage: String? = null,
    @SerializedName("publishedAt") var publishedAt: String? = null,
    @SerializedName("content") var content: String? = null

)