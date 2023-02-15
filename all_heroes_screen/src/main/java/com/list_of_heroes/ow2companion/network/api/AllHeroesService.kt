package com.list_of_heroes.ow2companion.network.api

import com.list_of_heroes.ow2companion.network.models.AllHeroesItem
import retrofit2.http.GET

interface AllHeroesService {

    @GET("heroes")
    suspend fun getAllHeroes(): List<AllHeroesItem>
}