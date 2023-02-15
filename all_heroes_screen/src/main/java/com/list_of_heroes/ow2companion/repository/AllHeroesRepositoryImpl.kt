package com.list_of_heroes.ow2companion.repository

import com.list_of_heroes.ow2companion.network.api.AllHeroesService
import com.list_of_heroes.ow2companion.network.models.AllHeroesItem

class AllHeroesRepositoryImpl(
    private val allHeroesService: AllHeroesService
) : AllHeroesRepository {

    override suspend fun getAllHeroesFromRole(): List<AllHeroesItem> {
        return allHeroesService.getAllHeroes()
    }
}