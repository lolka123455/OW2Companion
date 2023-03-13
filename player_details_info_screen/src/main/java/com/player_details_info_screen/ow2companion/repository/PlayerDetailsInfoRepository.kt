package com.player_details_info_screen.ow2companion.repository

import com.player_details_info_screen.ow2companion.network.models.competitive.PlayerStatsSummaryCompetitive
import com.player_details_info_screen.ow2companion.network.models.playerBasicInfo.FoundPlayerBasicInfo
import com.player_details_info_screen.ow2companion.network.models.quickplay.PlayerStatsSummaryQuickPlay

interface PlayerDetailsInfoRepository {

    suspend fun getExactFoundPlayerBasicInfo(player_id: String): List<FoundPlayerBasicInfo>

    suspend fun getPlayerSummaryDetailsInfoCompetitive(
        player_id: String
    ): PlayerStatsSummaryCompetitive

    suspend fun getPlayerSummaryDetailsInfoQuickPlay(
        player_id: String
    ): PlayerStatsSummaryQuickPlay
}