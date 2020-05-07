package com.goobar.io.ad340

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

enum class TempDisplaySetting {
    Fahrenheit, Celsius
}

class TempDisplaySettingManager(context: Context) {
    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun updateSetting(setting: TempDisplaySetting) {
        preferences.edit().putString("key_temp_display", setting.name).commit()
    }

    fun getTempDisplaySetting() : TempDisplaySetting {
        val settingValue = preferences.getString("key_temp_display", TempDisplaySetting.Fahrenheit.name) ?: TempDisplaySetting.Fahrenheit.name
        return TempDisplaySetting.valueOf(settingValue)
    }
}