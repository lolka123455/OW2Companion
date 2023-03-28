package com.allHeroesList.ow2companion.repository

import com.allHeroesList.ow2companion.network.models.AllHeroesItem

interface AllHeroesRepository {

    suspend fun getAllTankFromRole(): List<AllHeroesItem>

    suspend fun getAllDamageFromRole(): List<AllHeroesItem>

    suspend fun getAllSupportFromRole(): List<AllHeroesItem>
}