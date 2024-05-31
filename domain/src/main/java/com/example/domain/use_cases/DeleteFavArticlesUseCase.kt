package com.example.domain.use_cases

interface DeleteFavArticlesUseCase {

    suspend operator fun invoke(title:String)
}