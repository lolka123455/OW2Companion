package com.game_modes.ow2companion.di

import com.game_modes.ow2companion.network.api.GameModesService
import com.game_modes.ow2companion.repository.GameModesRepository
import com.game_modes.ow2companion.repository.GameModesRepositoryImpl
import com.game_modes.ow2companion.usecases.GetAllGameModesListUseCase
import com.game_modes.ow2companion.viewmodels.GameModesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val GameModesModule = module {

    single {
        provideGameModesService(retrofit = get())
    }

    single<GameModesRepository> {
        GameModesRepositoryImpl(gameModesService = get())
    }

    factory {
        GetAllGameModesListUseCase(get())
    }

    viewModel {
        GameModesViewModel(get())
    }

}

fun provideGameModesService(retrofit: Retrofit): GameModesService =
    retrofit.create(GameModesService::class.java)