package com.details_for_hero_screen.ow2companion.network.api

import com.details_for_hero_screen.ow2companion.network.models.DetailsInfoHero
import retrofit2.http.GET

interface DetailsHeroService {

    @GET("gamemodes")
    suspend fun getDetailsHero(): List<DetailsInfoHero>
}