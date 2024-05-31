package com.example.presentation.screens.onboardingScreen

import com.example.domain.model.Category

sealed class OnboardingIntent {

    data class SetSelectedCountry(val selectedCountry: String) : OnboardingIntent()
    data class ToggleFavorite(val category: Category) : OnboardingIntent()
    object SaveOnBoardingStatus : OnboardingIntent()

}
