package com.sarthak.animelist.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.sarthak.animelist.R

private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

private val fontName = GoogleFont("Poppins")

val poppinsFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = provider, weight = FontWeight.Normal),
    Font(googleFont = fontName, fontProvider = provider, weight = FontWeight.Medium),
    Font(googleFont = fontName, fontProvider = provider, weight = FontWeight.Bold)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp
    ),
    displayMedium = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Bold, // Large headline
        fontSize = 24.sp // Display large size
    ),
    headlineMedium = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Normal, // Regular weight
        fontSize = 20.sp // Display medium size
    ),
    labelSmall = TextStyle(
        fontFamily = poppinsFamily,
        fontWeight = FontWeight.Normal, // Regular weight
        fontSize = 12.sp
    )
)
