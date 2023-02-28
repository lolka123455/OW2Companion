package com.details_for_hero_screen.ow2companion.repository

import com.details_for_hero_screen.ow2companion.network.models.DetailsInfoHero

interface DetailsHeroRepository {

    suspend fun getFullDetailsForHero(hero_key : String): List<DetailsInfoHero>
}