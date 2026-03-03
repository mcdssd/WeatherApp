package com.example.weatherapp_marina.api

import android.util.Log
import com.example.weatherapp_marina.model.APIWeatherForecast
import com.example.weatherapp_marina.model.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherService {
    private var weatherAPI: WeatherServiceAPI

    init {
        val retrofitAPI = Retrofit.Builder().baseUrl(WeatherServiceAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        weatherAPI = retrofitAPI.create(WeatherServiceAPI::class.java)
    }
    fun getName(lat: Double, lng: Double, onResponse : (String?) -> Unit ) {
        search("$lat,$lng") { loc -> onResponse (loc?.name) }
    }

    fun getLocation(name: String, onResponse: (lat:Double?, long:Double?) -> Unit) {
        search(name) { loc -> onResponse (loc?.lat, loc?.lon) }
    }
    private fun search(query: String, onResponse : (APILocation?) -> Unit) {
        val call: Call<List<APILocation>?> = weatherAPI.search(query)
        call.enqueue(object : Callback<List<APILocation>?> {
            override fun onResponse(call: Call<List<APILocation>?>,
                                    response: Response<List<APILocation>?>
            ) {
                onResponse(response.body()?.let {if (it.isNotEmpty()) it[0] else null})
            }
            override fun onFailure(call: Call<List<APILocation>?>,t: Throwable) {
                Log.w("WeatherApp WARNING", "" + t.message)
                onResponse(null)
            }
        })
    }

    fun getWeather(name: String, onResponse: (APICurrentWeather?) -> Unit){
        val call: Call<APICurrentWeather?> = weatherAPI.weather(name)
        enqueue(call) { onResponse.invoke(it) }
    }

    fun getForecast(name: String, onResponse : (APIWeatherForecast?) -> Unit) {
        val call: Call<APIWeatherForecast?> = weatherAPI.forecast(name)
        enqueue(call) { onResponse.invoke(it) }
    }

    private fun <T> enqueue(call : Call<T?>, onResponse : ((T?) -> Unit)? = null){
        call.enqueue(object : Callback<T?> {
            override fun onResponse(call: Call<T?>, response: Response<T?>) {
                val obj: T? = response.body()
                onResponse?.invoke(obj)
            }
            override fun onFailure(call: Call<T?>, t: Throwable) {
                Log.w("WeatherApp WARNING", "" + t.message)
            }
        })
    }
}

data class APICondition (
    var text : String? = null,
    var icon : String? = null
)
data class APIWeather (
    var last_updated: String? = null,
    var temp_c : Double? = 0.0,
    var maxtemp_c: Double? = 0.0,
    var mintemp_c: Double? = 0.0,
    var condition : APICondition? = null
)
data class APICurrentWeather (
    var location : APILocation? = null,
    var current : APIWeather? = null
)
fun APICurrentWeather.toWeather() : Weather {
    return Weather (
        date = current?.last_updated?:"...",
        desc = current?.condition?.text?:"...",
        temp = current?.temp_c?:-1.0,
        imgUrl = "https:" + current?.condition?.icon
    )
}