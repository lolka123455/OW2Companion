package com.gameModes.ow2companion.repository

import com.gameModes.ow2companion.network.models.GameModesItem

interface GameModesRepository {

    suspend fun getGameModes(): List<GameModesItem>
}