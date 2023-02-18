package com.details_for_hero_screen.ow2companion.usecases

import com.details_for_hero_screen.ow2companion.repository.DetailsHeroRepository

class GetFullDetailsForHeroUseCase(private val detailsHeroRepository: DetailsHeroRepository) {

    suspend fun invoke() = detailsHeroRepository.getFullDetailsForHero()
}