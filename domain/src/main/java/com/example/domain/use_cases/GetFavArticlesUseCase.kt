package com.example.domain.use_cases

import com.example.domain.Resource
import com.example.domain.model.FavArticleModel
import kotlinx.coroutines.flow.Flow

interface GetFavArticlesUseCase {

    operator fun invoke(): Flow<Resource<List<FavArticleModel>>>
}