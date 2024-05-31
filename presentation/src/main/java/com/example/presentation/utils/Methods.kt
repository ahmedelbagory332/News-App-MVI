package com.example.presentation.utils

import java.text.SimpleDateFormat
import java.util.Locale

fun getTime(timestamp: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return outputFormat.format(inputFormat.parse(timestamp)!!)

}