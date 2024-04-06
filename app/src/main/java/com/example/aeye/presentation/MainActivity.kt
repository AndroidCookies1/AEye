package com.example.aeye.presentation

import LocaleUtils
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.aeye.data.SharedPreferencesManagerImpl
import com.example.aeye.presentation.settings.SettingsScreen
import com.example.aeye.presentation.settings.SettingsScreenViewModel
import com.example.aeye.presentation.settings.SettingsScreenViewModelFactory
import com.example.aeye.ui.theme.AEyeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val application = requireNotNull(this.application)
        val sharedPreferencesManager = SharedPreferencesManagerImpl(this)
        val viewModelFactory = SettingsScreenViewModelFactory(application, sharedPreferencesManager)
        val settingsScreenVM = ViewModelProvider(this, viewModelFactory).get(SettingsScreenViewModel::class.java)
        setContent {
            AEyeTheme {
                SettingsScreen(
                    viewModel = settingsScreenVM,
                    onLocaleChange = { recreateActivity() },
                ).Content()
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleUtils.updateBaseContextLocale(newBase))
    }

    private fun recreateActivity() {
        val intent = intent
        finish()
        startActivity(intent)
    }
}
