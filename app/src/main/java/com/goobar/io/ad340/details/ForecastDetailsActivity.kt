package com.goobar.io.ad340.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.goobar.io.ad340.R
import com.goobar.io.ad340.formatTempForDisplay

class ForecastDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_details)

        setTitle(R.string.forecast_details)

        val tempText = findViewById<TextView>(R.id.tempText)
        val descriptionText = findViewById<TextView>(R.id.descriptionText)

        val temp = intent.getFloatExtra("key_temp", 0f)
        tempText.text = formatTempForDisplay(temp)
        descriptionText.text = intent.getStringExtra("key_description")
    }
}
