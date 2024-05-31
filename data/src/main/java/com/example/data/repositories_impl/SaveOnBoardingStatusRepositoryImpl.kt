package com.example.data.repositories_impl

import android.content.SharedPreferences
import android.util.Log
import com.example.domain.model.OnBoardingModel
import com.example.domain.repositories.SaveOnBoardingStatusRepository
import javax.inject.Inject


class SaveOnBoardingStatusRepositoryImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    SaveOnBoardingStatusRepository {


    override fun saveOnBoardingStatus(
        onBoardingModel: OnBoardingModel
    ) {

        sharedPreferences.edit()
            .putBoolean("firstTime", onBoardingModel.firstTime)
            .putString("country", onBoardingModel.country)
            .putStringSet("categories", onBoardingModel.categories)
            .apply()
    }


    override fun getOnBoardingStatus(): OnBoardingModel {
        val firstTime = sharedPreferences.getBoolean("firstTime", true)
        val country = sharedPreferences.getString("country", "")
        val categories = sharedPreferences.getStringSet("categories", setOf())
        return OnBoardingModel(firstTime, country!!, categories!!)
    }

    override fun saveCurrentLang(lang: String) {
        sharedPreferences.edit()
            .putString("currentLang", lang)
            .apply()
    }

    override fun getCurrentLang(): String {
        val lang = sharedPreferences.getString("currentLang", null) ?: "en"
        Log.d("TAG", "SaveOnBoardingStatusRepositoryImpl: $lang")
        return lang

    }
}