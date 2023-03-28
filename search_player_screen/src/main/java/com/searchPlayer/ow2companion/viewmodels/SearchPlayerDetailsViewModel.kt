package com.searchPlayer.ow2companion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.searchPlayer.ow2companion.network.models.SearchPlayer
import com.searchPlayer.ow2companion.usecases.GetAllSimilarPlayersFoundedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchPlayerDetailsViewModel(
    private val getAllSimilarPlayersFoundedUseCase: GetAllSimilarPlayersFoundedUseCase
) : ViewModel() {

    private val _similarPlayersFounded = MutableStateFlow<List<SearchPlayer>>(emptyList())
    val similarPlayersFounded: StateFlow<List<SearchPlayer>> = _similarPlayersFounded

    fun getSimilarPlayersFounded(player_id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _similarPlayersFounded.value = getAllSimilarPlayersFoundedUseCase.invoke(player_id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}