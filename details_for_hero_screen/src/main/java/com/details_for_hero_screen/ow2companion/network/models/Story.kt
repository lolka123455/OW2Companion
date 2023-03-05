package com.details_for_hero_screen.ow2companion.network.models

data class Story(
    val chapters: List<Chapter>,
    val media: Media,
    val summary: String
)