package com.example.domain.use_cases_impl

import com.example.domain.model.ArticlesModel
import com.example.domain.repositories.CachedNewsRepository
import com.example.domain.use_cases.SaveFavArticlesUseCase
import javax.inject.Inject

class SaveFavArticlesUseCaseImpl @Inject constructor(
    private val cachedNewsRepository: CachedNewsRepository,
) : SaveFavArticlesUseCase {



    override suspend fun invoke(fav: ArticlesModel) {
            cachedNewsRepository.saveFavNews(fav)
    }

}