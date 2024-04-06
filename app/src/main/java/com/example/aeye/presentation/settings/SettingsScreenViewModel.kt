package com.example.aeye.presentation.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aeye.data.SharedPreferencesManager
import com.example.aeye.domain.Language

class SettingsScreenViewModel(
    application: Application,
    private val sharedPreferencesManager: SharedPreferencesManager
) : AndroidViewModel(application) {
    private val _selectedLanguage = MutableLiveData(sharedPreferencesManager.getSelectedLanguage())
    val selectedLanguage: LiveData<Language> = _selectedLanguage

    private val _isDropdownMenuExpanded: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDropdownMenuExpanded: LiveData<Boolean> = _isDropdownMenuExpanded

    fun onLanguageSelected(language: SupportedLanguage) {
        _selectedLanguage.value = language.language
        sharedPreferencesManager.setSelectedLanguage(language.language)
        _isDropdownMenuExpanded.value = false
    }
    fun onDropDownMenuDismiss() {
        _isDropdownMenuExpanded.value = false
    }
    fun expandDropDownMenu() {
        _isDropdownMenuExpanded.value = true
    }
}