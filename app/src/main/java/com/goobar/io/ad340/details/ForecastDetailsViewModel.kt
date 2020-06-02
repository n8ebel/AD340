package com.goobar.io.ad340.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

private val DATE_FORMAT = SimpleDateFormat("MM-dd-yyyy")

class ForecastDetailsViewModel : ViewModel() {

    private val _viewState: MutableLiveData<ForecastDetailsViewState> = MutableLiveData()
    val viewState: LiveData<ForecastDetailsViewState> = _viewState

    fun processArgs(args: ForecastDetailsFragmentArgs) {
        _viewState.value = ForecastDetailsViewState(
            temp = args.temp,
            description = args.description,
            date = DATE_FORMAT.format(Date(args.date * 1000)),
            iconUrl = "http://openweathermap.org/img/wn/${args.icon}@2x.png"
        )
    }
}