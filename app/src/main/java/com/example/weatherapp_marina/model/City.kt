package com.example.weatherapp_marina.model

import com.google.android.gms.maps.model.LatLng


data class City(
    val name: String,
    val location: LatLng? = null,
    val isMonitored: Boolean = false
    )

