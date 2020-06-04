package com.goobar.io.ad340.forecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil.api.load
import com.goobar.io.ad340.*
import com.goobar.io.ad340.api.CurrentWeather
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

private val DATE_FORMAT = SimpleDateFormat("MM-dd-yyyy")

/**
 * Displays the current forecast for the current saved location
 */
class CurrentForecastFragment : Fragment() {

    private val forecastRepository = ForecastRepository()
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    private lateinit var locationRepository: LocationRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_current_forecast, container, false)
        val locationName = view.findViewById<TextView>(R.id.locationName)
        val tempText = view.findViewById<TextView>(R.id.tempText)
        val dateText = view.findViewById<TextView>(R.id.dateText)
        val forecastIcon = view.findViewById<ImageView>(R.id.forecastIcon)

        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())

        // Create the observer which updates the UI in response to forecast updates
        val currentWeatherObserver = Observer<CurrentWeather> { currentWeather ->
            locationName.text = currentWeather.name
            tempText.text = formatTempForDisplay(currentWeather.forecast.temp, tempDisplaySettingManager.getTempDisplaySetting())
            dateText.text = DATE_FORMAT.format(Date(currentWeather.date * 1000))

            val iconId = currentWeather.weather[0].icon
            forecastIcon.load("http://openweathermap.org/img/wn/${iconId}@2x.png")
        }
        forecastRepository.currentWeather.observe(viewLifecycleOwner, currentWeatherObserver)

        val locationEntryButton: FloatingActionButton = view.findViewById(R.id.locationEntryButton)
        locationEntryButton.setOnClickListener {
            showLocationEntry()
        }

        locationRepository = LocationRepository(requireContext())
        val savedLocationObserver = Observer<Location> { savedLocation ->
            when (savedLocation) {
                is Location.Zipcode -> forecastRepository.loadCurrentForecast(savedLocation.zipcode)
            }
        }
        locationRepository.savedLocation.observe(viewLifecycleOwner, savedLocationObserver)

        return view
    }

    private fun showLocationEntry() {
        val action = CurrentForecastFragmentDirections.actionCurrentForecastFragmentToLocationEntryFragment()
        findNavController().navigate(action)
    }
}
