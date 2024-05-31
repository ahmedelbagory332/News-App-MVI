package com.example.domain.use_cases

interface SaveCurrentLangUseCase {

    operator fun invoke(lang: String)
}