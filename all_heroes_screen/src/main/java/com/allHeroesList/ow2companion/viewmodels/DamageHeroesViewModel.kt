package com.allHeroesList.ow2companion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.allHeroesList.ow2companion.network.models.AllHeroesItem
import com.allHeroesList.ow2companion.usecases.GetAllDamageListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DamageHeroesViewModel(
    private val getAllDamageListUseCase: GetAllDamageListUseCase
) : ViewModel() {

    private val _allHeroesList = MutableStateFlow<List<AllHeroesItem>>(emptyList())
    val allHeroesList: StateFlow<List<AllHeroesItem>> = _allHeroesList

    fun getAllHeroes() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                _allHeroesList.value = getAllDamageListUseCase.invoke()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}