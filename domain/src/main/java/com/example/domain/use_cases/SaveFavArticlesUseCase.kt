package com.example.domain.use_cases

import com.example.domain.model.ArticlesModel

interface SaveFavArticlesUseCase {

    suspend operator fun invoke(fav: ArticlesModel)
}