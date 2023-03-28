package com.playerDetailsInfoScreen.ow2companion.usecases

import com.playerDetailsInfoScreen.ow2companion.network.models.playerBasicInfo.FoundPlayerBasicInfo
import com.playerDetailsInfoScreen.ow2companion.repository.PlayerDetailsInfoRepository

class GetExactFoundPlayerBasicInfoUseCase(
    private val playerDetailsInfoRepository: PlayerDetailsInfoRepository
) {

    suspend fun invoke(player_id: String): FoundPlayerBasicInfo =
        playerDetailsInfoRepository.getExactFoundPlayerBasicInfo(player_id)
}