package com.example.aeye.domain

import java.util.Locale

sealed class Language(val locale: Locale) {
    object English : Language(Locale.ENGLISH)
    object Arabic : Language(Locale("ar"))

    companion object {
        fun getLanguageFromString(languageString: String): Language {
            return when (languageString.lowercase(Locale.ROOT)) {
                Arabic.locale.language -> Arabic
                else -> English
            }
        }
    }
}