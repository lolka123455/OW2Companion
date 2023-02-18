package com.details_for_hero_screen.ow2companion.di

import com.details_for_hero_screen.ow2companion.network.api.DetailsHeroService
import com.details_for_hero_screen.ow2companion.repository.DetailsHeroRepository
import com.details_for_hero_screen.ow2companion.repository.DetailsHeroRepositoryImpl
import com.details_for_hero_screen.ow2companion.usecases.GetFullDetailsForHeroUseCase
import com.details_for_hero_screen.ow2companion.viewmodels.DetailsHeroViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val DetailsHeroModule = module {

    single {
        provideDetailsHeroService(retrofit = get())
    }

    single<DetailsHeroRepository> {
        DetailsHeroRepositoryImpl(detailsHeroService = get())
    }

    factory {
        GetFullDetailsForHeroUseCase(get())
    }

    viewModel {
        DetailsHeroViewModel(get())
    }

}

fun provideDetailsHeroService(retrofit: Retrofit): DetailsHeroService =
    retrofit.create(DetailsHeroService::class.java)