package com.example.domain.use_cases

import com.example.domain.model.OnBoardingModel

interface SaveOnBoardingStatusUseCase {

    operator fun invoke(onBoardingModel: OnBoardingModel)
}