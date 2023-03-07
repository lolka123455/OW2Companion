package com.search_player.ow2companion.usecases

import com.search_player.ow2companion.network.models.SearchPlayer
import com.search_player.ow2companion.repository.SearchPlayerRepository

class GetAllSimilarPlayersFounded(
    private val searchPlayerRepository: SearchPlayerRepository
) {
    suspend fun invoke(player_id: String): SearchPlayer =
        searchPlayerRepository.getExactSearchPlayer(player_id)
}