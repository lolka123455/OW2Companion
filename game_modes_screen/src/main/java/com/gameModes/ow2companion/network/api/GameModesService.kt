package com.gameModes.ow2companion.network.api

import com.gameModes.ow2companion.network.models.GameModesItem
import retrofit2.http.GET

interface GameModesService {

    @GET("gamemodes")
    suspend fun getGameModes(): List<GameModesItem>
}