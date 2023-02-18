package com.game_modes.ow2companion.repository

import com.game_modes.ow2companion.network.api.GameModesService
import com.game_modes.ow2companion.network.models.GameModesItem

class GameModesRepositoryImpl(
    private val gameModesService: GameModesService
) : GameModesRepository {

    override suspend fun getGameModes(): List<GameModesItem> {
        return gameModesService.getGameModes()
    }
}
