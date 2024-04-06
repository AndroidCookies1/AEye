package com.example.aeye.presentation.settings

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aeye.data.SharedPreferencesManager

class SettingsScreenViewModelFactory (
    private val application: Application,
    private val sharedPreferencesManager: SharedPreferencesManager
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsScreenViewModel::class.java)) {
            return SettingsScreenViewModel(application, sharedPreferencesManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}