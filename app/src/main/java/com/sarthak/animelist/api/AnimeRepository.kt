package com.sarthak.animelist.api

//import com.sarthak.animelist.model.AnimeDetails
import com.sarthak.animelist.model.AnimeDetailssecond
import com.sarthak.animelist.model.AnimeResponse

class AnimeRepository {

    suspend fun getAnimeList(page: Int): AnimeResponse {
        return RetrofitInstance.api.getAnimeList(page)
    }

    suspend fun getAnimeDetails(animeId: Int): AnimeDetailssecond? {
        try {
            val response = RetrofitInstance.api.getAnimeDetails(animeId)

            return if (response.isSuccessful) {
                response.body()?.data?.let {
                    AnimeDetailssecond(
                        data = it
                    )
                }
            } else {
                println("Error: ${response.code()} - ${response.message()}")
                println("Response body: ${response.errorBody()?.string()}")
                null
            }
        } catch (e: Exception) {
            println("Exception occurred: ${e.message}")
            return null
        }
    }

}
