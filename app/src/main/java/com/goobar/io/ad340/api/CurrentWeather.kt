package com.goobar.io.ad340.api

import com.squareup.moshi.Json

data class Forecast(val temp: Float)
data class Coordinates(val lat: Float, val lon: Float)

/**
 * Api response for OpenWeatherMap's /weather endpoint
 */
data class CurrentWeather(
    @field:Json(name = "dt") val date: Long,
    val name: String,
    val coord: Coordinates,
    @field:Json(name = "main") val forecast: Forecast,
    val weather: List<WeatherDescription>
)