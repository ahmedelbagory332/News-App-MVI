package com.example.presentation.utils


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
    category: String,
    selected: Boolean = false,
    onSelected: ((category: String) -> Unit),
    modifier: Modifier,
    contentPadding: PaddingValues = PaddingValues(8.dp),
) {
    Surface(
        modifier = modifier.clickable {
            onSelected(category)
        }.padding(contentPadding),
        shape = RoundedCornerShape(10.dp),
        color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        shadowElevation = if (selected) 5.dp else 0.dp
    ) {
        Text(
            modifier = modifier.padding(contentPadding),
            text = category,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
        Divider()
    }
}