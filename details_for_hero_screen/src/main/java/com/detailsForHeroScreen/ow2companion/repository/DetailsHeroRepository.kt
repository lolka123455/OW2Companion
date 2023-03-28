package com.detailsForHeroScreen.ow2companion.repository

import com.detailsForHeroScreen.ow2companion.network.models.DetailsInfoHero

interface DetailsHeroRepository {

    suspend fun getFullDetailsForHero(hero_key: String): DetailsInfoHero
}