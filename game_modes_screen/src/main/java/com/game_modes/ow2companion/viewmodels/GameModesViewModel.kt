package com.game_modes.ow2companion.viewmodels

import androidx.lifecycle.ViewModel
import com.game_modes.ow2companion.usecases.GetAllGameModesListUseCase

class GameModesViewModel(
    private val getAllGameModesListUseCase: GetAllGameModesListUseCase
) : ViewModel() {

}