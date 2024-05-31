package com.example.presentation.utils

import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    menu: @Composable() (() -> Unit) = {}
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
            menu()
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            titleContentColor = Color.White,
            containerColor = MaterialTheme.colorScheme.primary,
           actionIconContentColor =  Color.White
        ),
    )
}