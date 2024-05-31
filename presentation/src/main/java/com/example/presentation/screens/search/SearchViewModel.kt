package com.example.presentation.screens.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Resource
import com.example.domain.model.ArticlesModel
import com.example.domain.use_cases.DeleteFavArticlesUseCase
import com.example.domain.use_cases.GetFavArticlesUseCase
import com.example.domain.use_cases.GetHeadLinesUseCase
import com.example.domain.use_cases.GetOnBoardingStatusUseCase
import com.example.domain.use_cases.SaveFavArticlesUseCase
import com.example.presentation.screens.favorites.FavoritesArticlesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getOnBoardingStatusUseCase: GetOnBoardingStatusUseCase,
    private val getHeadLinesUseCase: GetHeadLinesUseCase,
    private val getFavArticlesUseCase: GetFavArticlesUseCase,
    private val deleteFavArticlesUseCase: DeleteFavArticlesUseCase,
    private val saveFavArticlesUseCase: SaveFavArticlesUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(SearchLinesState())
    val state: State<SearchLinesState>
        get() = _state


    init {
        sendIntent(SearchIntent.LoadOnBoardingStatus)
        sendIntent(SearchIntent.LoadFavArticles)
    }

    fun sendIntent(intent: SearchIntent) {
        viewModelScope.launch {
            when (intent) {
                is SearchIntent.LoadOnBoardingStatus -> loadOnBoardingStatus()
                is SearchIntent.LoadSearchedArticles -> loadSearchedArticles(
                    intent.country,
                    intent.category,
                    intent.search
                )
                is SearchIntent.LoadFavArticles -> loadFavArticles()
                is SearchIntent.SaveFavArticle -> saveFavArticle(intent.article)
                is SearchIntent.DeleteFavArticle -> deleteFavArticle(intent.title)
                is SearchIntent.SetSelectedCategory -> setSelectedCategory(intent.category)
                is SearchIntent.ChangeQuerySearch -> changeQuery(intent.query)
                is SearchIntent.ChangeShowPlaceHolder -> changeShowPlaceHolder(intent.value)
            }
        }
    }


    private fun loadOnBoardingStatus() {
             val result = getOnBoardingStatusUseCase.invoke()
            _state.value = _state.value.copy(
                onBoardingStatus = result,
            )
        }


    private fun loadSearchedArticles(country: String, category: String, search: String) {
        getHeadLinesUseCase(country, category, search).onEach { result ->
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
                    _state.value.copy(
                        isLoading = true,
                    )
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


  private fun changeQuery(query: String) {
        _state.value = _state.value.copy(query = query)
    }

    private fun changeShowPlaceHolder(showPlaceHolder: Boolean){
        _state.value = _state.value.copy(showPlaceHolder = showPlaceHolder)
    }

}