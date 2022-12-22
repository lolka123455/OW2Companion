package com.game_modes.ow2companion.network.api.retrofitBuilder

import com.game_modes.ow2companion.network.interceptor.Interceptor.client
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameModesRetrofitBuilder {

    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    companion object {
        const val BASE_URL = "https://overfast-api.tekrop.fr/"
    }

}