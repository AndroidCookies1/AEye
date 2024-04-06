package com.example.aeye.data

import android.content.Context
import android.content.SharedPreferences
import com.example.aeye.R
import com.example.aeye.domain.Language

class SharedPreferencesManagerImpl(context: Context): SharedPreferencesManager {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.a_eye_shared_prefs_name),
        Context.MODE_PRIVATE
    )
    private val selectedLanguageKey = context.getString(R.string.selected_language_key)

    override fun getSelectedLanguage(): Language {
        val defaultLanguageString = Language.English.locale.language
        val languageString = sharedPreferences.getString(selectedLanguageKey, defaultLanguageString) ?: defaultLanguageString
        return Language.getLanguageFromString(languageString)
    }

    override fun setSelectedLanguage(language: Language) {
        sharedPreferences.edit().putString(selectedLanguageKey, language.locale.language).apply()
    }
}