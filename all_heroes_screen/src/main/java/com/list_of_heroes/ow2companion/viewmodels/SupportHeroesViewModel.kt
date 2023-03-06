package com.list_of_heroes.ow2companion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.list_of_heroes.ow2companion.network.models.AllHeroesItem
import com.list_of_heroes.ow2companion.usecases.GetAllSupportListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SupportHeroesViewModel(
    private val getAllSupportListUseCase: GetAllSupportListUseCase
) : ViewModel() {

    private val _allHeroesList = MutableStateFlow<List<AllHeroesItem>>(emptyList())
    val allHeroesList: StateFlow<List<AllHeroesItem>> = _allHeroesList

    fun getAllHeroes() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                _allHeroesList.value = getAllSupportListUseCase.invoke()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}