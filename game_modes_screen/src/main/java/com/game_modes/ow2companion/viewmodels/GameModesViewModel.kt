package com.game_modes.ow2companion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game_modes.ow2companion.network.models.GameModesItem
import com.game_modes.ow2companion.usecases.GetAllGameModesListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GameModesViewModel(
    private val getAllGameModesUseCase: GetAllGameModesListUseCase
) : ViewModel() {

    private val _gameModeList = MutableStateFlow(listOf<GameModesItem>())
    val gameModeList: StateFlow<List<GameModesItem>>
    get() = _gameModeList.asStateFlow()

    fun getGameModes() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val result = getAllGameModesUseCase.invoke()
                _gameModeList.tryEmit(result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}