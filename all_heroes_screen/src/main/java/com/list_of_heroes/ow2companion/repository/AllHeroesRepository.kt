package com.list_of_heroes.ow2companion.repository

import com.list_of_heroes.ow2companion.network.models.AllHeroesItem

interface AllHeroesRepository {

    suspend fun getAllTankFromRole() : List<AllHeroesItem>

    suspend fun getAllDamageFromRole() : List<AllHeroesItem>

    suspend fun getAllSupportFromRole() : List<AllHeroesItem>

}