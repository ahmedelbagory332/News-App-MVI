package com.example.presentation.screens.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.example.presentation.screens.headlines.HeadLinesActivity
import com.example.presentation.screens.onboardingScreen.OnboardingActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            if (viewModel.onBoardingStatus.value.firstTime) {
                startActivity(Intent(this, OnboardingActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, HeadLinesActivity::class.java))
                finish()
            }
    }

}
