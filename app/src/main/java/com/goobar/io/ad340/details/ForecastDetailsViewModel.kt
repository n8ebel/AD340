package com.goobar.io.ad340.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.util.*

private val DATE_FORMAT = SimpleDateFormat("MM-dd-yyyy")

class ForecastDetailsViewModelFactory(private val args: ForecastDetailsFragmentArgs) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForecastDetailsViewModel::class.java)) {
            return ForecastDetailsViewModel(args) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class ForecastDetailsViewModel(args: ForecastDetailsFragmentArgs) : ViewModel() {

    private val _viewState: MutableLiveData<ForecastDetailsViewState> = MutableLiveData()
    val viewState: LiveData<ForecastDetailsViewState> = _viewState

    init {
        _viewState.value = ForecastDetailsViewState(
            temp = args.temp,
            description = args.description,
            date = DATE_FORMAT.format(Date(args.date * 1000)),
            iconUrl = "http://openweathermap.org/img/wn/${args.icon}@2x.png"
        )
    }
}