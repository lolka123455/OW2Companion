package com.gameModes.ow2companion

import com.gameModes.ow2companion.network.api.GameModesService
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class GameModesServiceTest {

    private lateinit var gameModesService: GameModesService

    @Before
    fun createService() {
        gameModesService = Retrofit.Builder()
            .baseUrl("https://overfast-api.tekrop.fr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GameModesService::class.java)
    }

    @Test
    fun getGameModes() = runBlocking {
        val gameModes = gameModesService.getGameModes()

        assertEquals(gameModes.size, 8)

        for (gameMode in gameModes) {
            assertNotNull(gameMode.description)
            assertNotNull(gameMode.icon)
            assertNotNull(gameMode.name)
            assertNotNull(gameMode.screenshot)
        }
    }
}