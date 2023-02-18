package com.details_for_hero_screen.ow2companion.repository

import com.details_for_hero_screen.ow2companion.network.api.DetailsHeroService
import com.details_for_hero_screen.ow2companion.network.models.DetailsInfoHero

class DetailsHeroRepositoryImpl(
    private val detailsHeroService: DetailsHeroService
) : DetailsHeroRepository {

    override suspend fun getFullDetailsForHero(): List<DetailsInfoHero> {
        return detailsHeroService.getDetailsHero()
    }
}