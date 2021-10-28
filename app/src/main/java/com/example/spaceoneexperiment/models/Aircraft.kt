package com.example.spaceoneexperiment.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Aircraft(
    val id : String,
    var name: String = "",
    var manufacturer : String = "",
    var manufacturingYear : String = "",
) : Parcelable


data class AircraftDTO(
    var name: String = "",
    var manufacturer: String = "",
    var manufacturingYear : String = "",
)
