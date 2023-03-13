package com.player_details_info_screen.ow2companion.network.models.playerBasicInfo

data class FoundPlayerBasicInfo(
    val avatar: String,
    val competitive: Competitive,
    val endorsement: Endorsement,
    val privacy: String,
    val title: String,
    val username: String
)