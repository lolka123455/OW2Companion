package com.game_modes.ow2companion.usecases

import com.game_modes.ow2companion.repository.GameModesRepository

class GetAllGameModesListUseCase(private val gameModesRepository: GameModesRepository) {

    suspend fun execute() = gameModesRepository.getGameModes()
}