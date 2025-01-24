package com.sarthak.animelist.presentation.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.sarthak.animelist.api.AnimeRepository
import com.sarthak.animelist.model.AnimeDetailssecond
import com.sarthak.animelist.presentation.components.TitleAppBar


@Composable
fun AnimeDetailScreen(
    navController: NavController,
    malID: Int,
) {
    val animeDetails = remember { mutableStateOf<AnimeDetailssecond?>(null) }
    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(malID) {
        isLoading.value = true
        try {
            animeDetails.value = AnimeRepository().getAnimeDetails(malID)
        } catch (e: Exception) {
            animeDetails.value = null
        } finally {
            isLoading.value = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        TitleAppBar(
            title = "Anime Info - $malID",
            navigationIcon = null,
            onNavigationClick = null
        )

        if (isLoading.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            animeDetails.value?.let { details ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Text(
                            text = details.data.title,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    item {
                        Image(
                            painter = rememberAsyncImagePainter(details.data.images.jpg.large_image_url),
                            contentDescription = "Anime Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                    }

                    details.data.trailer?.url?.let { trailerUrl ->
                        item {
                            Button(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl))
                                    navController.context.startActivity(intent)
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "Watch Trailer")
                            }
                        }
                    }

                    details.data.episodes?.let { episodes ->
                        item {
                            Text(
                                text = "Episodes: $episodes",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                    details.data.duration?.let { duration ->
                        item {
                            Text(
                                text = "Duration: $duration",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                    details.data.rating?.let { rating ->
                        item {
                            Text(
                                text = "Rating: $rating",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                    details.data.rank?.let { rank ->
                        item {
                            Text(
                                text = "Rank: #$rank",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                    details.data.synopsis?.let { synopsis ->
                        item {
                            Text(
                                text = "Synopsis: $synopsis",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                    details.data.year?.let { year ->
                        item {
                            Text(
                                text = "Aired Year: $year",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            } ?: run {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Error loading anime details",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
