package com.example.domain.use_cases

import com.example.domain.model.OnBoardingModel

interface GetOnBoardingStatusUseCase {

    operator fun invoke(): OnBoardingModel
}