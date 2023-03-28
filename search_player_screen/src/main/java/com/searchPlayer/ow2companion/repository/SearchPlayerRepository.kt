package com.searchPlayer.ow2companion.repository

import com.searchPlayer.ow2companion.network.models.SearchPlayer

interface SearchPlayerRepository {

    suspend fun getExactSearchPlayer(player_id: String): List<SearchPlayer>
}