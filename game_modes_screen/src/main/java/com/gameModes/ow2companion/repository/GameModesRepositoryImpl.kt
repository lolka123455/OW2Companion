package com.gameModes.ow2companion.repository

import com.gameModes.ow2companion.network.api.GameModesService
import com.gameModes.ow2companion.network.models.GameModesItem

class GameModesRepositoryImpl(
    private val gameModesService: GameModesService
) : GameModesRepository {

    override suspend fun getGameModes(): List<GameModesItem> {
        return gameModesService.getGameModes()
    }
}
