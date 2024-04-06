package com.example.aeye.data

import com.example.aeye.domain.Language

interface SharedPreferencesManager {
    fun getSelectedLanguage(): Language
    fun setSelectedLanguage(language: Language)
}