package com.detailsForHeroScreen.ow2companion.network.api

import com.detailsForHeroScreen.ow2companion.network.models.DetailsInfoHero
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsHeroService {

    @GET("/heroes/{hero_key}")
    suspend fun getDetailsHero(
        @Path("hero_key") string: String
    ): DetailsInfoHero
}