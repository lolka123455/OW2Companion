package com.playerDetailsInfoScreen.ow2companion.network.models.quickplay

data class PlayerStatsSummaryQuickPlay(
    val general: GeneralStats,
    val roles: RoleStats,
    val heroes: Map<String, HeroStats>
)
