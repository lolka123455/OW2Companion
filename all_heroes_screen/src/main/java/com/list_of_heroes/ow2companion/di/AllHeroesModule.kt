package com.list_of_heroes.ow2companion.di

import com.list_of_heroes.ow2companion.network.api.AllHeroesService
import com.list_of_heroes.ow2companion.repository.AllHeroesRepository
import com.list_of_heroes.ow2companion.repository.AllHeroesRepositoryImpl
import com.list_of_heroes.ow2companion.usecases.GetAllHeroesListUseCase
import org.koin.dsl.module
import retrofit2.Retrofit

val AllHeroesModule = module {

    single {
        provideAllHeroesService(retrofit = get())
    }

    single <AllHeroesRepository> {
        AllHeroesRepositoryImpl(allHeroesService = get())
    }

    factory {
        GetAllHeroesListUseCase(get())
    }


}

fun provideAllHeroesService(retrofit: Retrofit): AllHeroesService =
    retrofit.create(AllHeroesService::class.java)
