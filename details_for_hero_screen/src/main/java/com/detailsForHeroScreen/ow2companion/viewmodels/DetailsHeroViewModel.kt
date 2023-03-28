package com.detailsForHeroScreen.ow2companion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.detailsForHeroScreen.ow2companion.network.models.DetailsInfoHero
import com.detailsForHeroScreen.ow2companion.usecases.GetFullDetailsForHeroUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsHeroViewModel(
    private val getFullDetailsForHeroUseCase: GetFullDetailsForHeroUseCase
) : ViewModel() {

    private val _detailsHero = MutableStateFlow<DetailsInfoHero?>(null)
    val detailsHero: StateFlow<DetailsInfoHero?> = _detailsHero

    fun getDetailsHero(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _detailsHero.value = getFullDetailsForHeroUseCase.invoke(name)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}