package com.detailsForHeroScreen.ow2companion.di

import com.detailsForHeroScreen.ow2companion.network.api.DetailsHeroService
import com.detailsForHeroScreen.ow2companion.repository.DetailsHeroRepository
import com.detailsForHeroScreen.ow2companion.repository.DetailsHeroRepositoryImpl
import com.detailsForHeroScreen.ow2companion.usecases.GetFullDetailsForHeroUseCase
import com.detailsForHeroScreen.ow2companion.viewmodels.DetailsHeroViewModel
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