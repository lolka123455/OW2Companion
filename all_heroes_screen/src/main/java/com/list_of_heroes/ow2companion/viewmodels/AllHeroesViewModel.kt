package com.list_of_heroes.ow2companion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.list_of_heroes.ow2companion.network.models.AllHeroesItem
import com.list_of_heroes.ow2companion.usecases.GetAllHeroesListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllHeroesViewModel(
    private val getAllGameModesListUseCase: GetAllHeroesListUseCase
) : ViewModel() {

    private val _allHeroesList = MutableStateFlow(listOf<AllHeroesItem>())
    val allHeroesList: StateFlow<List<AllHeroesItem>>
        get() = _allHeroesList.asStateFlow()

    fun getAllHeroes() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val result = getAllGameModesListUseCase.invoke()
                _allHeroesList.tryEmit(result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}