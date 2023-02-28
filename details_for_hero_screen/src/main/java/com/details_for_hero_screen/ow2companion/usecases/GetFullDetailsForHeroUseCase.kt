package com.details_for_hero_screen.ow2companion.usecases

import com.details_for_hero_screen.ow2companion.network.models.DetailsInfoHero
import com.details_for_hero_screen.ow2companion.repository.DetailsHeroRepository

class GetFullDetailsForHeroUseCase(private val detailsHeroRepository: DetailsHeroRepository) {

    suspend fun invoke(hero_key: String): List<DetailsInfoHero> =
        detailsHeroRepository.getFullDetailsForHero(hero_key)
}