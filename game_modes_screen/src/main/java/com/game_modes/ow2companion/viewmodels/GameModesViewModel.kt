package com.game_modes.ow2companion.viewmodels

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game_modes.ow2companion.network.models.GameModesItem
import com.game_modes.ow2companion.network.models.GameModesList
import com.game_modes.ow2companion.usecases.GetAllGameModesListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameModesViewModel(
    private val getAllGameModesListUseCase: GetAllGameModesListUseCase
) : ViewModel() {

    val gameModesItems: StateFlow<List<GameModesItem>>
        get () = _gameModesItems.asStateFlow()
    private val _gameModesItems: MutableStateFlow<List<GameModesItem>> =
        MutableStateFlow(listOf())

    init {
        getGameModesList()
    }

    private fun getGameModesList() {
        try {
            viewModelScope.launch {
                val result = getAllGameModesListUseCase.invoke()
                _gameModesItems.tryEmit(listOf(result))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}