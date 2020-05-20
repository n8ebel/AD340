package com.goobar.io.ad340

import java.util.*

/**
 * Represents a forecast (for any duration) for a specific location
 */
data class DailyForecast(
    val date: Date,
    val temp: Float,
    val description: String
)