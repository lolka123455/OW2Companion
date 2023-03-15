package com.ow2companion

import android.app.Application
import com.details_for_hero_screen.ow2companion.di.DetailsHeroModule
import com.game_modes.ow2companion.di.GameModesModule
import com.list_of_heroes.ow2companion.di.AllHeroesModule
import com.player_details_info_screen.ow2companion.di.PlayerDetailsInfoModule
import com.search_player.ow2companion.di.SearchPlayerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                GameModesModule,
                NetworkModule,
                AllHeroesModule,
                DetailsHeroModule,
                SearchPlayerModule,
                PlayerDetailsInfoModule
            )
        }
    }
}