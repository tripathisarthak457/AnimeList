package com.sarthak.animelist.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sarthak.animelist.R
import com.sarthak.animelist.presentation.components.LottieAnimationLoader

@Composable
fun WelcomeScreen(
    navController: NavController
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val lottieSize = screenWidth

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimationLoader(
            assetName = R.raw.japanese_hi,
            modifier = Modifier
                .size(lottieSize)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(id = R.string.welcome_message),
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController.navigate("AnimeListScreen")
            },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(screenHeight * 0.05f)
        ) {
            Text(text = stringResource(id = R.string.get_started))
        }
    }
}