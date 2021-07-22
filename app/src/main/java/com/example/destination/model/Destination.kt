package com.example.destination.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Destination(
    val title : String,
    val rate : Float,
    val location : String,
    val description : String,
    val image : Int

) : Parcelable