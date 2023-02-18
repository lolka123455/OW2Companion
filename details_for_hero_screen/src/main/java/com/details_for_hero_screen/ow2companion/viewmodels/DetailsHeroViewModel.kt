package com.details_for_hero_screen.ow2companion.viewmodels

import androidx.lifecycle.ViewModel
import com.details_for_hero_screen.ow2companion.usecases.GetFullDetailsForHeroUseCase

class DetailsHeroViewModel(
    private val getFullDetailsForHeroUseCase: GetFullDetailsForHeroUseCase
) : ViewModel() {

}