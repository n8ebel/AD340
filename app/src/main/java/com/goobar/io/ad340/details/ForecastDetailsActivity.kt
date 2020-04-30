package com.goobar.io.ad340.details

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.goobar.io.ad340.R
import com.goobar.io.ad340.TempDisplaySetting
import com.goobar.io.ad340.TempDisplaySettingManager
import com.goobar.io.ad340.formatTempForDisplay

class ForecastDetailsActivity : AppCompatActivity() {

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_details)

        tempDisplaySettingManager = TempDisplaySettingManager(this)

        setTitle(R.string.forecast_details)

        bindForecastData()
    }

    private fun bindForecastData() {
        val tempText = findViewById<TextView>(R.id.tempText)
        val descriptionText = findViewById<TextView>(R.id.descriptionText)

        val temp = intent.getFloatExtra("key_temp", 0f)
        tempText.text = formatTempForDisplay(temp, tempDisplaySettingManager.getTempDisplaySetting())
        descriptionText.text = intent.getStringExtra("key_description")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.tempDisplaySetting -> {
                showTempDisplaySettingDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showTempDisplaySettingDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
            .setTitle("Choose Display Units")
            .setMessage("Choose which temperature unit to use for temperature display")
            .setPositiveButton("F°", object: DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    tempDisplaySettingManager.updateSetting(TempDisplaySetting.Fahrenheit)
                }
            })
            .setNeutralButton("C°") { _,_ ->
                tempDisplaySettingManager.updateSetting(TempDisplaySetting.Celsius)
            }
            .setOnDismissListener {
                Toast.makeText(this, "Setting will take affect on app restart", Toast.LENGTH_SHORT).show()
            }

        dialogBuilder.show()
    }
}
