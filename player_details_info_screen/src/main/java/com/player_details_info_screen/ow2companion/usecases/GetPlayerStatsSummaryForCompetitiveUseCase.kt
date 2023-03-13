package com.player_details_info_screen.ow2companion.usecases

import com.player_details_info_screen.ow2companion.network.models.competitive.PlayerStatsSummaryCompetitive
import com.player_details_info_screen.ow2companion.repository.PlayerDetailsInfoRepository

class GetPlayerStatsSummaryForCompetitiveUseCase(
    private val playerDetailsInfoRepository: PlayerDetailsInfoRepository
) {

    suspend fun invoke(player_id: String): PlayerStatsSummaryCompetitive =
        playerDetailsInfoRepository.getPlayerSummaryDetailsInfoCompetitive(player_id)
}