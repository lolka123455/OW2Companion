package com.list_of_heroes.ow2companion.network.api

import com.list_of_heroes.ow2companion.network.models.AllHeroesItem
import retrofit2.http.GET

interface AllHeroesService {

    @GET("heroes?role=tank&locale=en-us")
    suspend fun getAllTankHeroes(): List<AllHeroesItem>

    @GET("heroes?role=damage&locale=en-us")
    suspend fun getAllDamageHeroes(): List<AllHeroesItem>

    @GET("heroes?role=support&locale=en-us")
    suspend fun getAllSupportHeroes(): List<AllHeroesItem>
}