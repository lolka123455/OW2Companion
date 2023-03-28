package com.playerDetailsInfoScreen.ow2companion.repository

import com.playerDetailsInfoScreen.ow2companion.network.api.PlayerDetailsInfoService
import com.playerDetailsInfoScreen.ow2companion.network.models.competitive.PlayerStatsSummaryCompetitive
import com.playerDetailsInfoScreen.ow2companion.network.models.playerBasicInfo.FoundPlayerBasicInfo
import com.playerDetailsInfoScreen.ow2companion.network.models.quickplay.PlayerStatsSummaryQuickPlay

class PlayerDetailsInfoRepositoryImpl(
    private val playerDetailsInfoService: PlayerDetailsInfoService
) : PlayerDetailsInfoRepository {

    override suspend fun getExactFoundPlayerBasicInfo(
        player_id: String
    ): FoundPlayerBasicInfo {
        return playerDetailsInfoService.getPlayerBasicInfo(player_id)
    }

    override suspend fun getPlayerSummaryDetailsInfoCompetitive(
        player_id: String
    ): PlayerStatsSummaryCompetitive {
        return playerDetailsInfoService.getPlayerDetailsInfoCompetitive(player_id)
    }

    override suspend fun getPlayerSummaryDetailsInfoQuickPlay(
        player_id: String
    ): PlayerStatsSummaryQuickPlay {
        return playerDetailsInfoService.getPlayerDetailsInfoQuickPlay(player_id)
    }
}