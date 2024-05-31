package com.example.presentation.screens.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.OnBoardingModel
import com.example.domain.use_cases.GetOnBoardingStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getOnBoardingStatusUseCase: GetOnBoardingStatusUseCase
) : ViewModel() {

    private val _onBoardingModel = mutableStateOf(OnBoardingModel())
    val onBoardingStatus = _onBoardingModel

    fun sendIntent(intent: MainIntent) {
        viewModelScope.launch {
            when (intent) {
                is MainIntent.GetOnBoardingStatus -> getOnBoardingStatus()
            }
        }
    }

    init {
        sendIntent(MainIntent.GetOnBoardingStatus)
    }

    private fun getOnBoardingStatus() {
        viewModelScope.launch {
            val result = getOnBoardingStatusUseCase.invoke()
            _onBoardingModel.value = result
        }
    }

}