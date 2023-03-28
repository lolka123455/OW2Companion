package com.playerDetailsInfoScreen.ow2companion.network.models.quickplay

data class RoleStats(
    val damage: RoleDetailsStats,
    val support: RoleDetailsStats,
    val tank: RoleDetailsStats
)