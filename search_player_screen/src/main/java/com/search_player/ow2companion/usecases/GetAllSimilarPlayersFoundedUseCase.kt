package com.search_player.ow2companion.usecases

import com.search_player.ow2companion.network.models.SearchPlayer
import com.search_player.ow2companion.repository.SearchPlayerRepository

class GetAllSimilarPlayersFoundedUseCase(
    private val searchPlayerRepository: SearchPlayerRepository
) {
    suspend fun invoke(player_id: String): List<SearchPlayer> =
        searchPlayerRepository.getExactSearchPlayer(player_id)
}