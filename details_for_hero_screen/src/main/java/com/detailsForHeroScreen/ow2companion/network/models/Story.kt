package com.detailsForHeroScreen.ow2companion.network.models

data class Story(
    val chapters: List<Chapter>,
    val media: Media,
    val summary: String
)