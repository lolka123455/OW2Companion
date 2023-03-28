package com.gameModes.ow2companion

import com.gameModes.ow2companion.adapter.GameModesListAdapter
import com.gameModes.ow2companion.network.models.GameModesItem
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GameModesListAdapterTest {

    private lateinit var gameModesItem: GameModesItem

    @Before
    fun setUp() {
        gameModesItem = GameModesItem(
            "Teams battle to take control of a robot and push it toward the enemy base.",
            "https://blz-contentstack-images.akamaized.net/v3/assets/blt9c12f249ac15c7ec/blt054b513cd6e95acf/62fd5b4a8972f93d1e325243/Push.svg",
            "Push",
            "https://blz-contentstack-images.akamaized.net/v3/assets/blt9c12f249ac15c7ec/blt93eefb6e91347639/62fc2d9eda42240856c1459c/Toronto_Push.jpg"
        )
    }

    @Test
    fun testGameModesListAdapter() {
        val gameModesListAdapter = GameModesListAdapter(gameModesItem)

        assertEquals(gameModesListAdapter.type, R.layout.games_modes_item)
        assertEquals(gameModesListAdapter.identifier, gameModesItem.hashCode().toLong())
    }
}