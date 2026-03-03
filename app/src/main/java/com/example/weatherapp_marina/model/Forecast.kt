package com.example.weatherapp_marina.model

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp_marina.api.APILocation
import com.example.weatherapp_marina.api.APIWeather
import java.text.DecimalFormat


data class Forecast (
    val date: String, val weather: String,
    val tempMin: Double, val tempMax: Double, val imgUrl: String,
)

data class APIWeatherForecast (
    var location: APILocation? = null,
    var current: APIWeather? = null,
    //current: APIWeatherForecast?
    var forecast: APIForecast? = null
)
data class APIForecast ( var forecastday: List<APIForecastDay>? = null )
data class APIForecastDay ( var date: String? = null, var day: APIWeather? = null )
fun APIWeatherForecast.toForecast(): List<Forecast>? {
    return forecast?.forecastday?.map {
        Forecast(
            date = it.date?:"00-00-0000",
            weather = it.day?.condition?.text?:"Erro carregando!",
            tempMin = it.day?.mintemp_c?:-1.0,
            tempMax = it.day?.maxtemp_c?:-1.0,
            imgUrl = ("https:" + it.day?.condition?.icon)
        )
    }
}

