package com.sarthak.animelist

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sarthak.animelist.presentation.screens.AnimeDetailScreen
import com.sarthak.animelist.presentation.screens.AnimeListScreen
import com.sarthak.animelist.presentation.screens.WelcomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "WelcomeScreen"
    ) {
        composable("WelcomeScreen") {
            WelcomeScreen(navController)
        }
        composable("AnimeListScreen") {
            AnimeListScreen(navController)
        }
        composable("AnimeDetailScreen") {
            AnimeDetailScreen(navController)
        }
    }

}