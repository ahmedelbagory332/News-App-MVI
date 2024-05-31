package com.example.domain.use_cases_impl

import com.example.domain.Resource
import com.example.domain.model.ArticlesModel
import com.example.domain.repositories.CachedNewsRepository
import com.example.domain.repositories.GetHeadLinesRepository
import com.example.domain.use_cases.GetHeadLinesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHeadLinesUseCaseImpl @Inject constructor(
    private val getHeadLinesRepository: GetHeadLinesRepository,
    private val cachedNewsRepository: CachedNewsRepository,
) : GetHeadLinesUseCase {


    override fun invoke(country: String, category: String,search: String): Flow<Resource<List<ArticlesModel>>> = flow {
        try {
            emit(Resource.Loading<List<ArticlesModel>>())
            val headLines = getHeadLinesRepository.getRemoteHeadLines(country, category,search)
            cachedNewsRepository.cacheNews(headLines.articles)
            cachedNewsRepository.getAllCachedNews().collect {
                emit(Resource.Success<List<ArticlesModel>>(it))
            }
        } catch(e: Exception) {
            emit(Resource.Error<List<ArticlesModel>>("${e.localizedMessage} : An unexpected error happened"))
            cachedNewsRepository.getAllCachedNews().collect {
                emit(Resource.Success<List<ArticlesModel>>(it))
            }
        }
    }

}