package com.search_player.ow2companion.di

import com.search_player.ow2companion.network.api.SearchPlayerService
import com.search_player.ow2companion.repository.SearchPlayerRepository
import com.search_player.ow2companion.repository.SearchPlayerRepositoryImpl
import com.search_player.ow2companion.usecases.GetAllSimilarPlayersFoundedUseCase
import com.search_player.ow2companion.viewmodels.SearchPlayerDetailsViewModel
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