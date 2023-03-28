package com.playerDetailsInfoScreen.ow2companion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playerDetailsInfoScreen.ow2companion.network.models.competitive.PlayerStatsSummaryCompetitive
import com.playerDetailsInfoScreen.ow2companion.usecases.GetPlayerStatsSummaryForCompetitiveUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CompetitivePlayerDetailsInfoViewModel(
    private val getPlayerStatsSummaryForCompetitiveUseCase: GetPlayerStatsSummaryForCompetitiveUseCase
) : ViewModel() {

    private val _playerStatsSummaryCompetitive =
        MutableStateFlow<PlayerStatsSummaryCompetitive?>(null)
    val playerStatsSummaryCompetitive: StateFlow<PlayerStatsSummaryCompetitive?> =
        _playerStatsSummaryCompetitive

    fun getViewPagerCompetitiveDetailsInfo(player_id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _playerStatsSummaryCompetitive.value =
                    getPlayerStatsSummaryForCompetitiveUseCase.invoke(player_id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}