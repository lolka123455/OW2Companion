package com.playerDetailsInfoScreen.ow2companion.network.models.quickplay

data class RoleDetailsStats(
    val games_played: Int,
    val time_played: Int,
    val winrate: Double,
    val kda: Double,
    val total: TotalStats,
    val average: AverageStats
)
