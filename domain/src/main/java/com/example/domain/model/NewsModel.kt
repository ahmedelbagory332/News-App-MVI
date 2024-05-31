package com.example.domain.model


//data class NewsModel(
//     val id: Long?,
//    val title: String?,
//    val date: String?,
//     val image: String?,
//     val url: String?,
//    val source_newspaper: String?,
//    val Short_description: String?,
//)
data class NewsModel(
    var articles: List<ArticlesModel> = listOf()

)
