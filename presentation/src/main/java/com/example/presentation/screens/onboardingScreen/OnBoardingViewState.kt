package com.example.presentation.screens.onboardingScreen

import com.example.domain.model.Category


data class OnBoardingViewState(
   var favoriteCategories: List<Category> = arrayListOf(),
   var selectedCountry:String = "",
   var allCategoriesList: List<Category> = allCategories,
   var countriesList: HashMap<String, String> = countries,
 )

private val allCategories = listOf(
    Category(1, "business"),
    Category(2, "entertainment"),
    Category(3, "general"),
    Category(4, "health"),
    Category(5, "science"),
    Category(6, "sports"),
    Category(7, "technology")
)

 val countries = hashMapOf(
    "United Arab Emirates" to "ae",
    "Argentina" to "ar",
    "Austria" to "at",
    "Australia" to "au",
    "Belgium" to "be",
    "Bulgaria" to "bg",
    "Brazil" to "br",
    "Canada" to "ca",
    "Switzerland" to "ch",
    "China" to "cn",
    "Colombia" to "co",
    "Cuba" to "cu",
    "Czech Republic" to "cz",
    "Germany" to "de",
    "Egypt" to "eg",
    "France" to "fr",
    "United Kingdom" to "gb",
    "Greece" to "gr",
    "Hong Kong" to "hk",
    "Hungary" to "hu",
    "Indonesia" to "id",
    "Ireland" to "ie",
    "Israel" to "il",
    "India" to "in",
    "Italy" to "it",
    "Japan" to "jp",
    "South Korea" to "kr",
    "Lithuania" to "lt",
    "Latvia" to "lv",
    "Morocco" to "ma",
    "Mexico" to "mx",
    "Malaysia" to "my",
    "Nigeria" to "ng",
    "Netherlands" to "nl",
    "Norway" to "no",
    "New Zealand" to "nz",
    "Philippines" to "ph",
    "Poland" to "pl",
    "Portugal" to "pt",
    "Romania" to "ro",
    "Serbia" to "rs",
    "Russia" to "ru",
    "Saudi Arabia" to "sa",
    "Sweden" to "se",
    "Singapore" to "sg",
    "Slovenia" to "si",
    "Slovakia" to "sk",
    "Thailand" to "th",
    "Turkey" to "tr",
    "Taiwan" to "tw",
    "Ukraine" to "ua",
    "United States" to "us",
    "Venezuela" to "ve",
    "South Africa" to "za"
)