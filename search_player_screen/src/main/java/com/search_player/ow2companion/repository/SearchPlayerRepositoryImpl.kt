package com.search_player.ow2companion.repository

import com.search_player.ow2companion.network.api.SearchPlayerService
import com.search_player.ow2companion.network.models.SearchPlayer

class SearchPlayerRepositoryImpl(
    private val searchPlayerService: SearchPlayerService
) : SearchPlayerRepository {

    override suspend fun getExactSearchPlayer(player_id: String): List<SearchPlayer> {
        return listOf(searchPlayerService.getSearchPlayer(player_id))
    }
}