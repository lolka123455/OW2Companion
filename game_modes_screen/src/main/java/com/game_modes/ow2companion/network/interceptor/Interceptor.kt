package com.game_modes.ow2companion.network.interceptor

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object Interceptor {

    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
}