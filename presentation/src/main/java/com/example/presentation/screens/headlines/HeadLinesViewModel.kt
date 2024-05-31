package com.example.presentation.screens.headlines

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Resource
import com.example.domain.model.ArticlesModel
import com.example.domain.use_cases.DeleteFavArticlesUseCase
import com.example.domain.use_cases.GetCurrentLangUseCase
import com.example.domain.use_cases.GetFavArticlesUseCase
import com.example.domain.use_cases.GetHeadLinesUseCase
import com.example.domain.use_cases.GetOnBoardingStatusUseCase
import com.example.domain.use_cases.SaveCurrentLangUseCase
import com.example.domain.use_cases.SaveFavArticlesUseCase
import com.example.presentation.screens.favorites.FavoritesArticlesState
import com.example.presentation.screens.onboardingScreen.countries
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HeadLinesViewModel @Inject constructor(
    private val getOnBoardingStatusUseCase: GetOnBoardingStatusUseCase,
    private val getHeadLinesUseCase: GetHeadLinesUseCase,
    private val getFavArticlesUseCase: GetFavArticlesUseCase,
    private val deleteFavArticlesUseCase: DeleteFavArticlesUseCase,
    private val saveFavArticlesUseCase: SaveFavArticlesUseCase,
    private val saveCurrentLangUseCase: SaveCurrentLangUseCase,
    private val getCurrentLangUseCase: GetCurrentLangUseCase,
) : ViewModel() {



    private val _state = mutableStateOf(HeadLinesState())
    val state: State<HeadLinesState>
        get() = _state


    init {
        sendIntent(HeadLinesIntent.LoadOnBoardingStatus)
        sendIntent(HeadLinesIntent.LoadFavArticles)
    }

     fun sendIntent(intent: HeadLinesIntent) {
         viewModelScope.launch {
            when (intent) {
                is HeadLinesIntent.LoadOnBoardingStatus -> loadOnBoardingStatus()
                is HeadLinesIntent.LoadHeadLines -> loadHeadLines(intent.country, intent.category)
                is HeadLinesIntent.LoadFavArticles -> loadFavArticles()
                is HeadLinesIntent.SaveFavArticle -> saveFavArticle(intent.article)
                is HeadLinesIntent.DeleteFavArticle -> deleteFavArticle(intent.title)
                is HeadLinesIntent.SetSelectedCategory -> setSelectedCategory(intent.category)
                is HeadLinesIntent.SetLanguagePreference -> setLanguagePreference(intent.language)
            }
        }
    }

    private fun loadOnBoardingStatus() {
        val result = getOnBoardingStatusUseCase.invoke()
        _state.value = _state.value.copy(
            onBoardingStatus = result,
            selectedCategory = result.categories.firstOrNull() ?: ""
        )
        countries[result.country]?.let { loadHeadLines(it, result.categories.first()) }
    }



    private fun loadHeadLines(country: String, category: String) {
        getHeadLinesUseCase(country, category).onEach { result ->
            _state.value = when (result) {
                is Resource.Success -> {
                    _state.value.copy(
                       headLines = result.data!!.sortedByDescending { it.publishedAt },
                        error = "",
                      isLoading = false,
                    )
                }
                is Resource.Error -> {
                    _state.value.copy(
                        error = result.message ?: "An unexpected error happened",
                        isLoading = false,
                    )
                }
                is Resource.Loading -> {
                    _state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }



    private fun loadFavArticles() {
        getFavArticlesUseCase().onEach { result ->
            _state.value = when (result) {
                is Resource.Success -> {
                    _state.value.copy(
                        favoritesArticlesState = FavoritesArticlesState(
                            articles = result.data!!,
                            error = "",
                            isLoading = false
                        ),
                    )
                }
                is Resource.Error -> {
                    _state.value.copy(
                        favoritesArticlesState = FavoritesArticlesState(
                            error = result.message ?: "An unexpected error happened",
                            isLoading = false
                        ),
                    )
                }
                is Resource.Loading -> {
                    _state.value.copy(
                        favoritesArticlesState = FavoritesArticlesState(
                            isLoading = true
                        ),
                    )
                }
            }
        }.launchIn(viewModelScope)
    }



    private suspend fun saveFavArticle(article: ArticlesModel) {
        viewModelScope.launch(Dispatchers.IO) {
            saveFavArticlesUseCase.invoke(article)
            loadFavArticles()
        }
    }

    private suspend fun deleteFavArticle(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavArticlesUseCase.invoke(title)
            loadFavArticles()
        }
    }


    private fun setSelectedCategory(category: String) {
        _state.value = _state.value.copy(selectedCategory = category)
    }

    private  fun setLanguagePreference(language: String) {
        saveCurrentLangUseCase(language)
        _state.value = _state.value.copy(languagePreference = language)
    }

    fun getLanguagePref(): String = getCurrentLangUseCase()


}