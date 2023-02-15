package com.list_of_heroes.ow2companion.usecases

import com.list_of_heroes.ow2companion.repository.AllHeroesRepository

class GetAllHeroesListUseCase(
    private val allHeroesRepository: AllHeroesRepository) {

    suspend fun invoke() = allHeroesRepository.getAllHeroesFromRole()
}