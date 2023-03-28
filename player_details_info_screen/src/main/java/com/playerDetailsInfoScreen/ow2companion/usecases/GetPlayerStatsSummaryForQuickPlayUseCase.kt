package com.playerDetailsInfoScreen.ow2companion.usecases

import com.playerDetailsInfoScreen.ow2companion.network.models.quickplay.PlayerStatsSummaryQuickPlay
import com.playerDetailsInfoScreen.ow2companion.repository.PlayerDetailsInfoRepository

class GetPlayerStatsSummaryForQuickPlayUseCase(
    private val playerDetailsInfoRepository: PlayerDetailsInfoRepository
) {

    suspend fun invoke(player_id: String): PlayerStatsSummaryQuickPlay =
        playerDetailsInfoRepository.getPlayerSummaryDetailsInfoQuickPlay(player_id)
}