import android.content.Context
import androidx.activity.ComponentActivity
import com.example.aeye.R
import com.example.aeye.domain.Language
import java.util.Locale

object LocaleUtils {
    fun updateBaseContextLocale(context: Context): Context {
        val prefs = context.getSharedPreferences(
            context.getString(R.string.a_eye_shared_prefs_name), ComponentActivity.MODE_PRIVATE
        )
        val defaultLanguageString = Language.English.locale.language
        val savedLanguage = prefs.getString(
            context.getString(R.string.selected_language_key),
            defaultLanguageString
        )
        val newLocale =
            Language.getLanguageFromString(savedLanguage ?: defaultLanguageString).locale

        Locale.setDefault(newLocale)
        val configuration = context.resources.configuration
        configuration.setLocale(newLocale)

        return context.createConfigurationContext(configuration)
    }
}