package com.example.domain.use_cases_impl

import com.example.domain.repositories.SaveOnBoardingStatusRepository
import com.example.domain.use_cases.GetCurrentLangUseCase
import javax.inject.Inject

class GetCurrentLangUseCaseImpl @Inject constructor(
    private val saveOnBoardingStatusRepository: SaveOnBoardingStatusRepository,
) : GetCurrentLangUseCase {

    override fun invoke(): String {
        return saveOnBoardingStatusRepository.getCurrentLang()
    }
}