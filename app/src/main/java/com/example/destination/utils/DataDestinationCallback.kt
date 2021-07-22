package com.example.destination.utils

import com.example.destination.model.Destination

interface DataDestinationCallback {

    fun setData(destination: Destination)
}