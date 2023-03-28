package com.gameModes.ow2companion.di

import com.gameModes.ow2companion.network.api.GameModesService
import com.gameModes.ow2companion.repository.GameModesRepository
import com.gameModes.ow2companion.repository.GameModesRepositoryImpl
import com.gameModes.ow2companion.usecases.GetAllGameModesListUseCase
import com.gameModes.ow2companion.viewmodels.GameModesViewModel
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