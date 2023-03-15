package com.player_details_info_screen.ow2companion.network.models.competitive

data class RoleStats(
    val damage: RoleDetailsStats,
    val support: RoleDetailsStats,
    val tank: RoleDetailsStats
)