package com.game_modes.ow2companion.network.api

import com.game_modes.ow2companion.network.models.GameModesItem
import com.game_modes.ow2companion.network.models.GameModesList
import retrofit2.http.GET

interface GameModesService {

    @GET("gamemodes")
    suspend fun getGameModes(): List<GameModesItem>
}