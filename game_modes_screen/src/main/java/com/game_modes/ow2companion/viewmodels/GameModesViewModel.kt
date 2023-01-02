package com.game_modes.ow2companion.viewmodels

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game_modes.ow2companion.network.models.GameModesItem
import com.game_modes.ow2companion.network.models.GameModesList
import com.game_modes.ow2companion.usecases.GetAllGameModesListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameModesViewModel(
    private val getAllGameModesListUseCase: GetAllGameModesListUseCase
) : ViewModel() {

    val gameModesItems: StateFlow<List<GameModesItem>>
        get () = _gameModesItems
    private val _gameModesItems: MutableStateFlow<List<GameModesItem>> =
        MutableStateFlow(ArrayList<GameModesItem>())

    init {
        getGameModesList()
    }

    private fun getGameModesList() {
        try {
            viewModelScope.launch {
                _gameModesItems.value = listOf(getAllGameModesListUseCase.invoke())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}