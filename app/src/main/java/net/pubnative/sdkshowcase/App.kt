package net.pubnative.sdkshowcase

import android.app.Application
import android.preference.PreferenceManager
import net.pubnative.sdk.core.Pubnative
import net.pubnative.sdkshowcase.settings.SettingsConstants

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Pubnative.setTestMode(true)
        Pubnative.init(applicationContext, APP_TOKEN)

        initialiseSettings()
    }

    fun initialiseSettings() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        if (!preferences.contains(SettingsConstants.SETTING_NATIVE_DEFAULT)) {
            preferences
                    .edit()
                    .putBoolean(SettingsConstants.SETTING_NATIVE_DEFAULT, true)
                    .apply()
        }

        if (!preferences.contains(SettingsConstants.SETTING_DEMMAND_TYPE)) {
            preferences
                    .edit()
                    .putInt(SettingsConstants.SETTING_DEMMAND_TYPE, SettingsConstants.DEMAND_TYPE_NATIVE)
                    .apply()
        }
    }
}