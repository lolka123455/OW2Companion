package com.search_player.ow2companion.repository

import com.search_player.ow2companion.network.models.SearchPlayer

interface SearchPlayerRepository {

    suspend fun getExactSearchPlayer(player_id: String): List<SearchPlayer>
}