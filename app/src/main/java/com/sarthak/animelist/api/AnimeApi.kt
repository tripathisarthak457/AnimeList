package com.sarthak.animelist.api

//import com.sarthak.animelist.model.AnimeDetails
//import com.sarthak.animelist.model.AnimeDetailsResponse
import com.sarthak.animelist.model.AnimeDetailssecond
import com.sarthak.animelist.model.AnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApi {

    @GET("top/anime")
    suspend fun getAnimeList(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 25
    ): AnimeResponse

    @GET("anime/{anime_id}")
    suspend fun getAnimeDetails(@Path("anime_id") animeId: Int): Response<AnimeDetailssecond>
}
