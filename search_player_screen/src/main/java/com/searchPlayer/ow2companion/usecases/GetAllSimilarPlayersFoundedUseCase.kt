package com.searchPlayer.ow2companion.usecases

import com.searchPlayer.ow2companion.network.models.SearchPlayer
import com.searchPlayer.ow2companion.repository.SearchPlayerRepository

class GetAllSimilarPlayersFoundedUseCase(
    private val searchPlayerRepository: SearchPlayerRepository
) {
    suspend fun invoke(player_id: String): List<SearchPlayer> =
        searchPlayerRepository.getExactSearchPlayer(player_id)
}