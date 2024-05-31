package com.example.presentation.screens.onboardingScreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.presentation.R
import com.example.presentation.screens.onboardingScreen.OnboardingIntent
import com.example.presentation.screens.onboardingScreen.OnboardingViewModel

@Composable
 fun Countries(
    viewModel: OnboardingViewModel,
) {
    val viewState = viewModel.viewState.collectAsState()
    Text(
        text = stringResource(R.string.choose_your_favorite_country),
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp,color = MaterialTheme.colorScheme.primary),
        modifier = Modifier.padding(10.dp)
    )
    LazyHorizontalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {

        items(viewState.value.countriesList.keys.toList()) { country ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable(onClick = {
                        viewModel.sendIntent(OnboardingIntent.SetSelectedCountry(country))
                    })
            ) {
                RadioButton(
                    selected = viewState.value.selectedCountry == country,
                    onClick = {
                        viewModel.sendIntent(OnboardingIntent.SetSelectedCountry(country))
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = country)
            }

        }
    }
}