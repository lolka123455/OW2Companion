package com.allHeroesList.ow2companion.di

import com.allHeroesList.ow2companion.network.api.AllHeroesService
import com.allHeroesList.ow2companion.repository.AllHeroesRepository
import com.allHeroesList.ow2companion.repository.AllHeroesRepositoryImpl
import com.allHeroesList.ow2companion.usecases.GetAllDamageListUseCase
import com.allHeroesList.ow2companion.usecases.GetAllSupportListUseCase
import com.allHeroesList.ow2companion.usecases.GetAllTankListUseCase
import com.allHeroesList.ow2companion.viewmodels.DamageHeroesViewModel
import com.allHeroesList.ow2companion.viewmodels.SupportHeroesViewModel
import com.allHeroesList.ow2companion.viewmodels.TankHeroesViewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val AllHeroesModule = module {

    single {
        provideAllHeroesService(retrofit = get())
    }

    single<AllHeroesRepository> {
        AllHeroesRepositoryImpl(allHeroesService = get())
    }

    factory {
        GetAllTankListUseCase(get())
    }

    factory {
        GetAllDamageListUseCase(get())
    }

    factory {
        GetAllSupportListUseCase(get())
    }

    factory {
        TankHeroesViewModel(get())
    }

    factory {
        DamageHeroesViewModel(get())
    }

    factory {
        SupportHeroesViewModel(get())
    }
}

fun provideAllHeroesService(retrofit: Retrofit): AllHeroesService =
    retrofit.create(AllHeroesService::class.java)
