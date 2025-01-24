package com.sarthak.animelist.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.sarthak.animelist.presentation.components.AnimeListItem
import com.sarthak.animelist.presentation.components.TitleAppBar
import com.sarthak.animelist.viewmodel.AnimeListViewModel

@Composable
fun AnimeListScreen(navController: NavHostController) {
    val viewModel: AnimeListViewModel = viewModel()
    val currentPage = remember { mutableIntStateOf(1) }

    LaunchedEffect(currentPage.intValue) {
        viewModel.fetchAnimeList(currentPage.intValue)
    }

    if (viewModel.animeList.isEmpty() && viewModel.errorMessage.value.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (viewModel.errorMessage.value.isNotEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = viewModel.errorMessage.value,
                color = Color.Red,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TitleAppBar(
                title = "Anime List - Page ${currentPage.intValue}",
                navigationIcon = null,
                onNavigationClick = null
            )
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(viewModel.animeList.size) { index ->
                    AnimeListItem(anime = viewModel.animeList[index], navController)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (currentPage.value > 1) {
                            currentPage.value -= 1
                        }
                    },
                    enabled = currentPage.value > 1
                ) {
                    Text("Previous")
                }
                Text(
                    text = "Page ${currentPage.value}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Button(
                    onClick = {
                        currentPage.value += 1
                    },
                    enabled = viewModel.animeList.isNotEmpty()
                ) {
                    Text("Next")
                }
            }
        }
    }
}
