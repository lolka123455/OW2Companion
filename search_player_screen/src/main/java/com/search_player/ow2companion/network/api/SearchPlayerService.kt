package com.search_player.ow2companion.network.api

import com.search_player.ow2companion.network.models.SearchPlayer
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchPlayerService {

    @GET("players/{player_id}/summary")
    suspend fun getSearchPlayer(
        @Path("player_id") string: String
    ): SearchPlayer

}