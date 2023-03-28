package com.playerDetailsInfoScreen.ow2companion.repository

import com.playerDetailsInfoScreen.ow2companion.network.models.competitive.PlayerStatsSummaryCompetitive
import com.playerDetailsInfoScreen.ow2companion.network.models.playerBasicInfo.FoundPlayerBasicInfo
import com.playerDetailsInfoScreen.ow2companion.network.models.quickplay.PlayerStatsSummaryQuickPlay

interface PlayerDetailsInfoRepository {

    suspend fun getExactFoundPlayerBasicInfo(player_id: String): FoundPlayerBasicInfo

    suspend fun getPlayerSummaryDetailsInfoCompetitive(
        player_id: String
    ): PlayerStatsSummaryCompetitive

    suspend fun getPlayerSummaryDetailsInfoQuickPlay(
        player_id: String
    ): PlayerStatsSummaryQuickPlay
}