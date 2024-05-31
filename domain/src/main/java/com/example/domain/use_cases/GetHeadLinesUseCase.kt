package com.example.domain.use_cases

import com.example.domain.Resource
import com.example.domain.model.ArticlesModel
import kotlinx.coroutines.flow.Flow

interface GetHeadLinesUseCase {

    operator fun invoke(country: String, category: String,search: String=""): Flow<Resource<List<ArticlesModel>>>
}