package com.detailsForHeroScreen.ow2companion.network.models

data class DetailsInfoHero(
    val abilities: List<Ability>,
    val description: String,
    val location: String,
    val name: String,
    val portrait: String,
    val role: String,
    val story: Story
)