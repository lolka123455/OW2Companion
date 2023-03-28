package com.allHeroesList.ow2companion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allHeroesList.ow2companion.network.models.AllHeroesItem
import com.allHeroesList.ow2companion.usecases.GetAllTankListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TankHeroesViewModel(
    private val getAllTankListUseCase: GetAllTankListUseCase
) : ViewModel() {

    private val _allHeroesList = MutableStateFlow<List<AllHeroesItem>>(emptyList())
    val allHeroesList: StateFlow<List<AllHeroesItem>> = _allHeroesList

    fun getAllHeroes() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _allHeroesList.value = getAllTankListUseCase.invoke()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}