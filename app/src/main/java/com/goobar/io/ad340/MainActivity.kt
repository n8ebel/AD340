package com.goobar.io.ad340

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.goobar.io.ad340.forecast.CurrentForecastFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

  private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    tempDisplaySettingManager = TempDisplaySettingManager(this)

    val toolbar: Toolbar = findViewById(R.id.toolbar)
    toolbar.setTitle(R.string.app_name)
    toolbar.inflateMenu(R.menu.settings_menu)
    setSupportActionBar(toolbar)

    val navController = findNavController(R.id.nav_host_fragment)
    findViewById<BottomNavigationView>(R.id.bottomNavigation).setupWithNavController(navController)
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
        showTempDisplaySettingDialog(this, tempDisplaySettingManager)
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }
}
