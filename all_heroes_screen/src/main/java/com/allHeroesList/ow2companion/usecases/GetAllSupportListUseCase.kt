package com.allHeroesList.ow2companion.usecases

import com.allHeroesList.ow2companion.repository.AllHeroesRepository

class GetAllSupportListUseCase(
    private val allHeroesRepository: AllHeroesRepository
) {
    suspend fun invoke() = allHeroesRepository.getAllSupportFromRole()
}