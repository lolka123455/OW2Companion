package com.search_player.ow2companion.network.models

data class SearchPlayer(
    val avatar: String,
    val competitive: Competitive,
    val endorsement: Endorsement,
    val privacy: String,
    val title: String,
    val username: String
)