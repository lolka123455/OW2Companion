package com.ow2companion

import android.app.Application
import com.allHeroesList.ow2companion.di.AllHeroesModule
import com.detailsForHeroScreen.ow2companion.di.DetailsHeroModule
import com.game_modes.ow2companion.di.GameModesModule
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