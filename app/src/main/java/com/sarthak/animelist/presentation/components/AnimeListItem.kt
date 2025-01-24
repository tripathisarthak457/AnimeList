package com.sarthak.animelist.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.sarthak.animelist.model.AnimeListItem

@Composable
fun AnimeListItem(anime: AnimeListItem, navController: NavHostController) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardHeight = screenWidth / 4
    val imageSize = cardHeight

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable {
                navController.navigate("AnimeDetailScreen/${anime.mal_id}")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .width(imageSize)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        anime.images?.jpg?.image_url ?: ""
                    ),
                    contentDescription = anime.title ?: "Anime Image",
                    modifier = Modifier
                        .size(imageSize)
                        .padding(4.dp)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Score: ${anime.score ?: "Not Available"}",
                        style = MaterialTheme.typography.labelSmall
                    )

                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = anime.title ?: "Title Not Available",
                    style = MaterialTheme.typography.headlineLarge,
                    fontSize = 22.sp,
                    maxLines = 2
                )
                Text(
                    text = "Also Known as: ${anime.title_english ?: "Not Available"}",
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 12.sp,
                    maxLines = 2
                )
                Text(
                    text = "Episodes: ${anime.episodes ?: "Not Available"}",
                    style = MaterialTheme.typography.displayMedium,
                    fontSize = 16.sp
                )
                Text(
                    text = "Genres: ${anime.genres?.joinToString { it.name } ?: "Not Available"}",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}
