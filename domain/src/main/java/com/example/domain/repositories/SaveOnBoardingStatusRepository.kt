package com.example.domain.repositories

import com.example.domain.model.OnBoardingModel

interface SaveOnBoardingStatusRepository {

    fun saveOnBoardingStatus(onBoardingModel: OnBoardingModel)

    fun getOnBoardingStatus():OnBoardingModel

    fun saveCurrentLang(lang:String)
    fun getCurrentLang():String

}