package com.player_details_info_screen.ow2companion.di

import com.player_details_info_screen.ow2companion.network.api.PlayerDetailsInfoService
import com.player_details_info_screen.ow2companion.repository.PlayerDetailsInfoRepository
import com.player_details_info_screen.ow2companion.repository.PlayerDetailsInfoRepositoryImpl
import com.player_details_info_screen.ow2companion.usecases.GetExactFoundPlayerBasicInfoUseCase
import com.player_details_info_screen.ow2companion.usecases.GetPlayerStatsSummaryForCompetitiveUseCase
import com.player_details_info_screen.ow2companion.usecases.GetPlayerStatsSummaryForQuickPlayUseCase
import com.player_details_info_screen.ow2companion.viewmodels.PersonalPlayerInfoDetailsViewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val PlayerDetailsInfoModule = module {

    single {
        providePlayerDetailsInfoService(retrofit = get())
    }

    single<PlayerDetailsInfoRepository> {
        PlayerDetailsInfoRepositoryImpl(playerDetailsInfoService = get())
    }

    factory {
        GetPlayerStatsSummaryForQuickPlayUseCase(get())
    }

    factory {
        GetPlayerStatsSummaryForCompetitiveUseCase(get())
    }

    factory {
        GetExactFoundPlayerBasicInfoUseCase(get())
    }

    factory {
        PersonalPlayerInfoDetailsViewModel(get())
    }

}

fun providePlayerDetailsInfoService(retrofit: Retrofit): PlayerDetailsInfoService =
    retrofit.create(PlayerDetailsInfoService::class.java)