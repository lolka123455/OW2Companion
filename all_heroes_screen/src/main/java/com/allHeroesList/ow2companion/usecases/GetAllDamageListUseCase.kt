package com.allHeroesList.ow2companion.usecases

import com.allHeroesList.ow2companion.repository.AllHeroesRepository

class GetAllDamageListUseCase(
    private val allHeroesRepository: AllHeroesRepository
) {
    suspend fun invoke() = allHeroesRepository.getAllDamageFromRole()
}