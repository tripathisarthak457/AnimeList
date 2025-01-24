package com.sarthak.animelist

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
        composable(
            route = "AnimeDetailScreen/{mal_id}",
            arguments = listOf(navArgument("mal_id") { type = NavType.IntType })
        ) { backStackEntry ->
            val malID = backStackEntry.arguments?.getInt("mal_id") ?: 0
            AnimeDetailScreen(
                malID = malID,
                navController = navController
            )
        }
    }
}

