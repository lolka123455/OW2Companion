package com.playerDetailsInfoScreen.ow2companion.usecases

import com.playerDetailsInfoScreen.ow2companion.network.models.competitive.PlayerStatsSummaryCompetitive
import com.playerDetailsInfoScreen.ow2companion.repository.PlayerDetailsInfoRepository

class GetPlayerStatsSummaryForCompetitiveUseCase(
    private val playerDetailsInfoRepository: PlayerDetailsInfoRepository
) {

    suspend fun invoke(player_id: String): PlayerStatsSummaryCompetitive =
        playerDetailsInfoRepository.getPlayerSummaryDetailsInfoCompetitive(player_id)
}