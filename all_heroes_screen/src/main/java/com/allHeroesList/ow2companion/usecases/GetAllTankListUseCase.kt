package com.allHeroesList.ow2companion.usecases

import com.allHeroesList.ow2companion.repository.AllHeroesRepository

class GetAllTankListUseCase(
    private val allHeroesRepository: AllHeroesRepository
) {
    suspend fun invoke() = allHeroesRepository.getAllTankFromRole()
}