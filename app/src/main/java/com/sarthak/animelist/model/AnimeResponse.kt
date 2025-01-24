package com.sarthak.animelist.model

data class AnimeResponse(
    val pagination: Pagination,
    val data: List<AnimeListItem>
)