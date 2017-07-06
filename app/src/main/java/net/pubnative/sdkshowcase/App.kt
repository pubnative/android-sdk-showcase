package net.pubnative.sdkshowcase

import android.app.Application
import net.pubnative.sdk.core.Pubnative

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Pubnative.setTestMode(true)
        Pubnative.init(applicationContext, APP_TOKEN)
    }
}