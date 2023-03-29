package com.detailsForHeroScreen.ow2companion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.detailsForHeroScreen.ow2companion.network.models.DetailsInfoHero
import com.detailsForHeroScreen.ow2companion.usecases.GetFullDetailsForHeroUseCase
import com.google.gson.Gson
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailsHeroViewModel(
    private val getFullDetailsForHeroUseCase: GetFullDetailsForHeroUseCase
) : ViewModel() {

    /**
     * Represents a validation error response from the server.
     *
     * @property details A list of [ValidationErrorDetail] objects containing information about the
     * validation errors.
     */
    private data class ValidationErrorResponse(
        val validationErrorDetail: List<ValidationErrorDetail>
    )

    /**
     * Represents a detail of a validation error.
     *
     * @property loc A list of objects representing the location of the validation error.
     * @property msg A string containing a message describing the validation error.
     * @property type A string representing the type of the validation error.
     */
    private data class ValidationErrorDetail(val loc: List<Any>, val msg: String, val type: String)

    private val _detailsHero = MutableStateFlow<DetailsInfoHero?>(null)
    val detailsHero: StateFlow<DetailsInfoHero?> = _detailsHero

    private val _serverResponse = MutableStateFlow<String?>(null)
    val serverResponse: StateFlow<String?> = _serverResponse

    internal fun getDetailsHero(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _detailsHero.value = getFullDetailsForHeroUseCase.invoke(name)
            } catch (e: Exception) {
                handleException(e)
            }
        }
    }

    private fun handleException(e: Exception) {
        val errorMessage = when (e) {
            is HttpException -> handleHttpException(e)
            is IOException -> "Network Error: ${e.message}"
            else -> "Error: ${e.message}"
        }
        _serverResponse.tryEmit(errorMessage)
    }

    private fun handleHttpException(e: HttpException): String {
        return when (e.code()) {
            422 -> {
                val errorBody = e.response()?.errorBody()?.string()
                val validationErrorResponse =
                    Gson().fromJson(errorBody, ValidationErrorResponse::class.java)
                "Validation Error: ${validationErrorResponse.validationErrorDetail}"
            }
            500 -> "Internal Server Error: ${e.message()}"
            504 -> "Blizzard Server Error: ${e.message()}"
            else -> "Server Error: ${e.message()}"
        }
    }

    internal fun clearServerResponse() {
        _serverResponse.tryEmit(null)
    }

    override fun onCleared() {
        super.onCleared()
        clearServerResponse()
    }
}