package com.player_details_info_screen.ow2companion.usecases

import com.player_details_info_screen.ow2companion.network.models.playerBasicInfo.FoundPlayerBasicInfo
import com.player_details_info_screen.ow2companion.repository.PlayerDetailsInfoRepository

class GetExactFoundPlayerBasicInfoUseCase(
    private val playerDetailsInfoRepository: PlayerDetailsInfoRepository
) {

    suspend fun invoke(player_id: String): List<FoundPlayerBasicInfo> =
        playerDetailsInfoRepository.getExactFoundPlayerBasicInfo(player_id)
}