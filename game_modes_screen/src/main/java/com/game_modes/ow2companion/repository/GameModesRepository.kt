package com.game_modes.ow2companion.repository

import com.game_modes.ow2companion.network.models.GameModesItem

interface GameModesRepository {

    suspend fun getGameModes(): List<GameModesItem>
}