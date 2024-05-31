package com.example.domain.use_cases_impl

import com.example.domain.repositories.SaveOnBoardingStatusRepository
import com.example.domain.use_cases.SaveCurrentLangUseCase
import javax.inject.Inject

class SaveCurrentLangUseCaseImpl @Inject constructor(
    private val saveOnBoardingStatusRepository: SaveOnBoardingStatusRepository,
) : SaveCurrentLangUseCase {


    override fun invoke(lang: String) {
        saveOnBoardingStatusRepository.saveCurrentLang(lang)
    }
}