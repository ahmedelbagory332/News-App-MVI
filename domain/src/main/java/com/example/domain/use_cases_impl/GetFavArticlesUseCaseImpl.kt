package com.example.domain.use_cases_impl

import com.example.domain.Resource
import com.example.domain.model.FavArticleModel
import com.example.domain.repositories.CachedNewsRepository
import com.example.domain.use_cases.GetFavArticlesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavArticlesUseCaseImpl @Inject constructor(
    private val cachedNewsRepository: CachedNewsRepository,
) : GetFavArticlesUseCase {


    override fun invoke(): Flow<Resource<List<FavArticleModel>>> = flow {
        try {
            emit(Resource.Loading<List<FavArticleModel>>())
            cachedNewsRepository.getAllFavCachedNews().collect {
                emit(Resource.Success<List<FavArticleModel>>(it))
            }
        } catch(e: Exception) {
            emit(Resource.Error<List<FavArticleModel>>("${e.localizedMessage} : An unexpected error happened"))
        }
    }

}