package com.example.presentation.screens.favorites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Resource
import com.example.domain.use_cases.DeleteFavArticlesUseCase
import com.example.domain.use_cases.GetFavArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavArticlesUseCase: GetFavArticlesUseCase,
    private val deleteFavArticlesUseCase: DeleteFavArticlesUseCase,
) : ViewModel() {


    private val _favState = mutableStateOf(FavoritesArticlesState())
    val favState: State<FavoritesArticlesState>
        get() = _favState

    init {
        sendIntent(FavoritesIntent.LoadFavArticles)
    }

    fun sendIntent(intent: FavoritesIntent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (intent) {
                is FavoritesIntent.LoadFavArticles -> loadFavArticles()
                is FavoritesIntent.DeleteFavArticle -> deleteFavArticle(intent.title)
            }
        }
    }


   private fun loadFavArticles() {
        getFavArticlesUseCase().onEach { result ->
            _favState.value = when (result) {
                is Resource.Success -> {
                    _favState.value.copy(
                        articles = result.data!!.sortedByDescending { it.publishedAt },
                        error = "",
                        isLoading = false,
                    )

                }
                is Resource.Error -> {
                    _favState.value.copy(
                        error = result.message ?: "An unexpected error happened",
                        isLoading = false,
                    )

                }
                is Resource.Loading -> {
                    _favState.value.copy(
                        error = result.message ?: "An unexpected error happened",
                        isLoading = true,
                    )
                }
            }
        }.launchIn(viewModelScope)

    }

    private suspend fun deleteFavArticle(title: String) {
       deleteFavArticlesUseCase.invoke(title)
        loadFavArticles()
    }

}