package com.sarthak.animelist.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieAnimationLoader(
    assetName: Int,
    modifier: Modifier = Modifier
) {
    val compositionResult = rememberLottieComposition(LottieCompositionSpec.RawRes(assetName))

    val progress = animateLottieCompositionAsState(
        composition = compositionResult.value,
        iterations = LottieConstants.IterateForever
    )

    if (compositionResult.value != null) {
        LottieAnimation(
            composition = compositionResult.value,
            progress = { progress.value },
            modifier = modifier
        )
    }
}