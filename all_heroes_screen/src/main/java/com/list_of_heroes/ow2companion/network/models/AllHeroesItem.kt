package com.list_of_heroes.ow2companion.network.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AllHeroesItem(
    val key: String,
    val name: String,
    val portrait: String,
    val role: String
) : Parcelable