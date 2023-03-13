package com.player_details_info_screen.ow2companion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.player_details_info_screen.ow2companion.network.models.playerBasicInfo.FoundPlayerBasicInfo
import com.player_details_info_screen.ow2companion.usecases.GetExactFoundPlayerBasicInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PersonalPlayerInfoDetailsViewModel(
    private val getExactFoundPlayerBasicInfoUseCase: GetExactFoundPlayerBasicInfoUseCase
) : ViewModel() {

    private val _exactFoundPlayerBasicInfo =
        MutableStateFlow<FoundPlayerBasicInfo?>(null)
    val exactFoundPlayerBasicInfo: StateFlow<FoundPlayerBasicInfo?> =
        _exactFoundPlayerBasicInfo

    fun getTitleExactFoundPlayerBasicInfo(player_id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _exactFoundPlayerBasicInfo.value =
                    getExactFoundPlayerBasicInfoUseCase.invoke(player_id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}