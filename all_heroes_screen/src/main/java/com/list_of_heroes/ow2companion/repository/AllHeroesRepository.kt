package com.list_of_heroes.ow2companion.repository

import com.list_of_heroes.ow2companion.network.models.AllHeroesItem

interface AllHeroesRepository {

    suspend fun getAllHeroesFromRole() : List<AllHeroesItem>

}