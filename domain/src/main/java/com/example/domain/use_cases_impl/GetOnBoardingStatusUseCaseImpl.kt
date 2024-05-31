package com.example.domain.use_cases_impl

import com.example.domain.model.OnBoardingModel
import com.example.domain.repositories.SaveOnBoardingStatusRepository
import com.example.domain.use_cases.GetOnBoardingStatusUseCase
import javax.inject.Inject

class GetOnBoardingStatusUseCaseImpl @Inject constructor(
    private val saveOnBoardingStatusRepository: SaveOnBoardingStatusRepository,
) : GetOnBoardingStatusUseCase {




    override fun invoke(): OnBoardingModel {
         return saveOnBoardingStatusRepository.getOnBoardingStatus()
    }
}