package com.searchPlayer.ow2companion.di

import com.searchPlayer.ow2companion.network.api.SearchPlayerService
import com.searchPlayer.ow2companion.repository.SearchPlayerRepository
import com.searchPlayer.ow2companion.repository.SearchPlayerRepositoryImpl
import com.searchPlayer.ow2companion.usecases.GetAllSimilarPlayersFoundedUseCase
import com.searchPlayer.ow2companion.viewmodels.SearchPlayerDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val SearchPlayerModule = module {

    single {
        provideSearchPlayerService(retrofit = get())
    }

    single<SearchPlayerRepository> {
        SearchPlayerRepositoryImpl(searchPlayerService = get())
    }

    factory {
        GetAllSimilarPlayersFoundedUseCase(get())
    }

    viewModel {
        SearchPlayerDetailsViewModel(get())
    }
}

fun provideSearchPlayerService(retrofit: Retrofit): SearchPlayerService =
    retrofit.create(SearchPlayerService::class.java)