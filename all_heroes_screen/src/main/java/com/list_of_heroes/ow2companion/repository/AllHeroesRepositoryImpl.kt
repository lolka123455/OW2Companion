package com.list_of_heroes.ow2companion.repository

import com.list_of_heroes.ow2companion.network.api.AllHeroesService
import com.list_of_heroes.ow2companion.network.models.AllHeroesItem

class AllHeroesRepositoryImpl(
    private val allHeroesService: AllHeroesService
) : AllHeroesRepository {

    override suspend fun getAllTankFromRole(): List<AllHeroesItem> {
        return allHeroesService.getAllTankHeroes()
    }

    override suspend fun getAllDamageFromRole(): List<AllHeroesItem> {
        return allHeroesService.getAllDamageHeroes()
    }

    override suspend fun getAllSupportFromRole(): List<AllHeroesItem> {
        return allHeroesService.getAllSupportHeroes()
    }

}