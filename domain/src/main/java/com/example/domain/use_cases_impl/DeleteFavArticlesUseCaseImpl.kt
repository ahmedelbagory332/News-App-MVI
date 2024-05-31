package com.example.domain.use_cases_impl

import com.example.domain.repositories.CachedNewsRepository
import com.example.domain.use_cases.DeleteFavArticlesUseCase
import javax.inject.Inject

class DeleteFavArticlesUseCaseImpl @Inject constructor(
    private val cachedNewsRepository: CachedNewsRepository,
) : DeleteFavArticlesUseCase {


    override suspend fun invoke(title: String) {
        cachedNewsRepository.deleteFavNews(title)
    }

}