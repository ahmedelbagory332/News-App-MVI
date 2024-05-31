package com.example.presentation.screens.favorites.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.domain.model.FavArticleModel
import com.example.presentation.R
import com.example.presentation.screens.favorites.FavoritesIntent
import com.example.presentation.screens.favorites.FavoritesViewModel
import com.example.presentation.theme.White
import com.example.presentation.utils.CoilImage
import com.example.presentation.utils.getTime


@Composable
fun FavItemArticle(item: FavArticleModel, favoritesViewModel: FavoritesViewModel) {
    val uriHandler = LocalUriHandler.current
    val favArticles = favoritesViewModel.favState.value.articles
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                item.url?.let { uriHandler.openUri(it) }
            },
        colors = CardDefaults.cardColors(
            containerColor = White,
        ),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                item.title?.let {
                    Text(
                        modifier = Modifier
                            .padding(5.dp)
                            .weight(1f),
                        text = it,
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }

                Icon(
                    if (favArticles.map { it.title }
                            .contains(item.title)) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    "Favorite",
                    modifier = Modifier
                        .clickable {
                            item.title?.let {
                                favoritesViewModel.sendIntent(FavoritesIntent.DeleteFavArticle(it))
                            }
                    },
                    tint = MaterialTheme.colorScheme.primary
                )

            }
            item.publishedAt?.let {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = getTime(it),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            CoilImage(
                data = item.urlToImage.toString(),
                contentDescription = "",
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.FillBounds,
            )


            item.sourceName?.let {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = it,
                    style = TextStyle(color = MaterialTheme.colorScheme.primary)
                )
            }
            item.description?.let {
                Text(
                    text = it.ifEmpty { stringResource(R.string.there_is_no_description_click_and_open_article) },
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}