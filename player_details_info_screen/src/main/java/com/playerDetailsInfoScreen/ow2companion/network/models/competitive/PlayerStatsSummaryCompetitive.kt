package com.playerDetailsInfoScreen.ow2companion.network.models.competitive

data class PlayerStatsSummaryCompetitive(
    val general: GeneralStats,
    val roles: RoleStats,
    val heroes: Map<String, HeroStats>
)
