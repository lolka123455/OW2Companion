package com.detailsForHeroScreen.ow2companion.usecases

import com.detailsForHeroScreen.ow2companion.network.models.DetailsInfoHero
import com.detailsForHeroScreen.ow2companion.repository.DetailsHeroRepository

class GetFullDetailsForHeroUseCase(private val detailsHeroRepository: DetailsHeroRepository) {

    suspend fun invoke(hero_key: String): DetailsInfoHero =
        detailsHeroRepository.getFullDetailsForHero(hero_key)
}