package com.player_details_info_screen.ow2companion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.player_details_info_screen.ow2companion.network.models.quickplay.PlayerStatsSummaryQuickPlay
import com.player_details_info_screen.ow2companion.usecases.GetPlayerStatsSummaryForQuickPlayUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuickPlayPlayerDetailsInfoViewModel(
    private val getPlayerStatsSummaryForQuickPlayUseCase: GetPlayerStatsSummaryForQuickPlayUseCase
) : ViewModel() {

    private val _playerStatsSummaryQuickPlay =
        MutableStateFlow<PlayerStatsSummaryQuickPlay?>(null)
    val playerStatsSummaryQuickPlay: StateFlow<PlayerStatsSummaryQuickPlay?> =
        _playerStatsSummaryQuickPlay

    fun getViewPagerQuickPlayDetailsInfo(player_id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _playerStatsSummaryQuickPlay.value =
                    getPlayerStatsSummaryForQuickPlayUseCase.invoke(player_id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}