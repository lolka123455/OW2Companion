package com.ow2companion

import android.app.Application
import com.game_modes.ow2companion.di.GameModesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        org.koin.core.context.startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                GameModesModule
            )
        }

    }
}