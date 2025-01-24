package com.sarthak.animelist.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarthak.animelist.api.AnimeRepository
import com.sarthak.animelist.model.AnimeListItem
import com.sarthak.animelist.model.AnimeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnimeListViewModel : ViewModel() {
    private val repository = AnimeRepository()

    var animeList = mutableStateListOf<AnimeListItem>()
    var isLoading = mutableStateOf(false)
    var errorMessage = mutableStateOf("")

    fun fetchAnimeList(page: Int) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val response: AnimeResponse = withContext(Dispatchers.IO) {
                    repository.getAnimeList(page)
                }
                animeList.clear()
                animeList.addAll(response.data)
            } catch (e: Exception) {
                errorMessage.value = e.message ?: "An error occurred"
            } finally {
                isLoading.value = false
            }
        }
    }
}