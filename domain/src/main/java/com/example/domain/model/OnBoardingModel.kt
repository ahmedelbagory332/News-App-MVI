package com.example.domain.model



data class OnBoardingModel(
    var firstTime: Boolean = true,
    var country: String = "",
    var categories: Set<String> = setOf()
)
