package com.example.aeye.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.sharp.ArrowDropDown
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.aeye.R
import com.example.aeye.ui.theme.AppColors

class SettingsScreen(
    val viewModel: SettingsScreenViewModel,
    val onLocaleChange: () -> Unit
) {
    @Composable
    fun Content() {
        val selectedLanguage by viewModel.selectedLanguage.observeAsState()
        val isDropdownMenuExpanded by viewModel.isDropdownMenuExpanded.observeAsState(false)
        Surface(
            modifier = Modifier.padding(24.dp),
            color = AppColors.windowBackground
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(top = 20.dp, start = 8.dp, end = 8.dp),
                    text = stringResource(
                        R.string.selected_language,
                        selectedLanguage?.locale?.displayLanguage ?: SupportedLanguage.ENGLISH.name
                    ),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
                Divider(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 8.dp)
                        .fillMaxWidth(),
                    color = AppColors.textSecondary.copy(alpha = 0.2f)
                )
                val localContext = LocalContext.current
                Box(
                    modifier = Modifier
                        .clickable(
                            onClick = { viewModel.expandDropDownMenu() },
                            indication = rememberRipple(bounded = true),
                            interactionSource = remember { MutableInteractionSource() }
                        )
                        .padding(top = 16.dp, bottom = 8.dp)
                        .semantics {
                            contentDescription = localContext.getString(R.string.cd_language_selector)
                        }
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().padding(8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.choose_language),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                        Icon(
                            imageVector = Icons.Sharp.ArrowDropDown,
                            contentDescription = stringResource(id = R.string.cd_open_language_menu),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
                DropdownMenu(
                    expanded = isDropdownMenuExpanded,
                    onDismissRequest = { viewModel.onDropDownMenuDismiss() },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(8.dp)
                        .background(color = AppColors.windowBackground.copy(alpha = 0.8f) ),
                ) {
                    SupportedLanguage.entries.forEach { language ->
                        val langLocale = language.language.locale
                        DropdownMenuItem(
                            text = {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(langLocale.getDisplayLanguage(langLocale))
                                    if (selectedLanguage?.locale == langLocale) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = stringResource(id = R.string.cd_selected),
                                            modifier = Modifier.size(24.dp),
                                            tint = AppColors.accent
                                        )
                                    }
                                }
                            },
                            onClick = {
                                viewModel.onLanguageSelected(language)
                                onLocaleChange()
                            }
                        )
                    }
                }
            }
        }
    }
}
