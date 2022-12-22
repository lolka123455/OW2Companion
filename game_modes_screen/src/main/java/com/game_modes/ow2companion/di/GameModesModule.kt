package com.game_modes.ow2companion.di

import com.game_modes.ow2companion.network.api.retrofitBuilder.GameModesRetrofitBuilder
import com.game_modes.ow2companion.repository.GameModesRepository
import com.game_modes.ow2companion.repository.GameModesRepositoryImpl
import com.game_modes.ow2companion.usecases.GetAllGameModesListUseCase
import com.game_modes.ow2companion.viewmodels.GameModesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val GameModesModule = module {

    single {
        GameModesRetrofitBuilder().provideRetrofitInstance()
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