package com.sarthak.animelist.model

data class AnimeListItem(
    val mal_id: Int,
    val url: String,
    val images: AnimeImages,
    val title: String,
    val title_english: String,
    val episodes: Int,
    val status: String,
    val score: Double,
    val synopsis: String,
    val genres: List<Genre>
)