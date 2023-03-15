package com.player_details_info_screen.ow2companion.usecases

import com.player_details_info_screen.ow2companion.network.models.quickplay.PlayerStatsSummaryQuickPlay
import com.player_details_info_screen.ow2companion.repository.PlayerDetailsInfoRepository

class GetPlayerStatsSummaryForQuickPlayUseCase(
    private val playerDetailsInfoRepository: PlayerDetailsInfoRepository
) {

    suspend fun invoke(player_id: String): PlayerStatsSummaryQuickPlay =
        playerDetailsInfoRepository.getPlayerSummaryDetailsInfoQuickPlay(player_id)
}