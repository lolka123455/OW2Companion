package com.player_details_info_screen.ow2companion.network.models.quickplay

data class HeroStats(
    val games_played: Int,
    val time_played: Int,
    val winrate: Double,
    val kda: Double,
    val total: TotalStats,
    val average: AverageStats
)