package com.game_modes.ow2companion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game_modes.ow2companion.network.models.GameModesItem
import com.game_modes.ow2companion.usecases.GetAllGameModesListUseCase
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class GameModesViewModel(
    private val getAllGameModesUseCase: GetAllGameModesListUseCase
) : ViewModel() {

    // Add a data class to deserialize validation error responses
    private data class ValidationError(val detail: List<Detail>)

    private data class Detail(val loc: List<Any>, val msg: String, val type: String)

    private val _gameModesList = MutableStateFlow(listOf<GameModesItem>())
    val gameModesList: StateFlow<List<GameModesItem>> = _gameModesList

    private val _serverResponse = MutableStateFlow<String?>(null)
    val serverResponse: StateFlow<String?> = _serverResponse

    fun getGameModes() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val result = getAllGameModesUseCase.invoke()
                _gameModesList.tryEmit(result)
                _serverResponse.tryEmit("Game Modes Retrieved Successfully")
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }

    private fun handleException(e: Exception) {
        when (e) {
            is HttpException -> {
                val errorMessage = when (e.code()) {
                    422 -> {
                        val errorBody = e.response()?.errorBody()?.string()
                        val validationError =
                            Gson().fromJson(errorBody, ValidationError::class.java)
                        "Validation Error: ${validationError.detail}"
                    }
                    500 -> {
                        "Internal Server Error: ${e.message()}"
                    }
                    504 -> {
                        "Blizzard Server Error: ${e.message()}"
                    }
                    else -> {
                        "Server Error: ${e.message()}"
                    }
                }
                _serverResponse.tryEmit(errorMessage)
            }
            is IOException -> {
                _serverResponse.tryEmit("Network Error: ${e.message}")
            }
            else -> {
                _serverResponse.tryEmit("Error: ${e.message}")
            }
        }
    }

    // Add a function to clear the server response state flow
    internal fun clearServerResponse() {
        //Clears the current state of _serverResponse
        _serverResponse.tryEmit(null)
    }

    override fun onCleared() {
        super.onCleared()
        clearServerResponse()
    }

}