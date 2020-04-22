package com.goobar.io.ad340

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

  private val forecastRepository = ForecastRepository()

  // region Setup Methods

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val zipcodeEditText: EditText = findViewById(R.id.zipcodeEditText)
    val enterButton: Button = findViewById(R.id.enterButton)

    enterButton.setOnClickListener {
      val zipcode: String = zipcodeEditText.text.toString()

      if (zipcode.length != 5) {
        Toast.makeText(this, R.string.zipcode_entry_error, Toast.LENGTH_SHORT).show()
      } else {
        forecastRepository.loadForecast(zipcode)
      }
    }

    val dailyForecastList: RecyclerView = findViewById(R.id.dailyForecastList)
    dailyForecastList.layoutManager = LinearLayoutManager(this)
    val dailyForecastAdapter = DailyForecastListAdapter() {
      val msg = getString(R.string.forecast_clicked_format, it.temp, it.description)
      Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
    dailyForecastList.adapter = dailyForecastAdapter

    // Create the observer which updates the UI in response to forecast updates
    val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->
      // update our list adapter
      dailyForecastAdapter.submitList(forecastItems)
    }
    forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)

  }

  override fun onStart() {
    super.onStart()
  }

  override fun onResume() {
    super.onResume()
  }

  // endregion Setup Methods

  // region Teardown Methods

  override fun onPause() {
    super.onPause()
  }

  override fun onStop() {
    super.onStop()
  }

  override fun onDestroy() {
    super.onDestroy()
  }

  // endregion Teardown Methods
}
