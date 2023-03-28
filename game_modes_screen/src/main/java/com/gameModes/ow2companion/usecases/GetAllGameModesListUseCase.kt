package com.gameModes.ow2companion.usecases

import com.gameModes.ow2companion.repository.GameModesRepository

class GetAllGameModesListUseCase(private val gameModesRepository: GameModesRepository) {

    suspend fun invoke() = gameModesRepository.getGameModes()
}