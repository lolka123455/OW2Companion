package com.game_modes.ow2companion.network.api.service

import com.game_modes.ow2companion.network.models.GameModesItem
import retrofit2.http.GET

interface GameModesService {

    @GET("gamemodes")
    suspend fun getGameModes(): GameModesItem
}