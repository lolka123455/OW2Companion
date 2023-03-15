package com.player_details_info_screen.ow2companion.network.api

import com.player_details_info_screen.ow2companion.network.models.playerBasicInfo.FoundPlayerBasicInfo
import com.player_details_info_screen.ow2companion.network.models.competitive.PlayerStatsSummaryCompetitive
import com.player_details_info_screen.ow2companion.network.models.quickplay.PlayerStatsSummaryQuickPlay
import retrofit2.http.GET
import retrofit2.http.Path

interface PlayerDetailsInfoService {

    @GET("players/{player_id}/summary")
    suspend fun getPlayerBasicInfo(
        @Path("player_id") string: String
    ): FoundPlayerBasicInfo

    @GET("/players/{player_id}/stats/summary?gamemode=competitive&platform=pc")
    suspend fun getPlayerDetailsInfoCompetitive(
        @Path("player_id") string: String
    ): PlayerStatsSummaryCompetitive

    @GET("/players/{player_id}/stats/summary?gamemode=quickplay&platform=pc")
    suspend fun getPlayerDetailsInfoQuickPlay(
        @Path("player_id") string: String
    ): PlayerStatsSummaryQuickPlay


}