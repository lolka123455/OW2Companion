package com.list_of_heroes.ow2companion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.list_of_heroes.ow2companion.network.models.AllHeroesItem
import com.list_of_heroes.ow2companion.usecases.GetAllTankListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TankHeroesViewModel(
    private val getAllTankListUseCase: GetAllTankListUseCase
) : ViewModel() {

    private val _allHeroesList = MutableStateFlow(listOf<AllHeroesItem>())
    val allHeroesList: StateFlow<List<AllHeroesItem>>
        get() = _allHeroesList.asStateFlow()

    fun getAllHeroes() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val result = getAllTankListUseCase.invoke()
                _allHeroesList.tryEmit(result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}