package net.pubnative.sdkshowcase.ui.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.pubnative.sdkshowcase.R

import kotlinx.android.synthetic.main.fragment_main.*
import net.pubnative.sdkshowcase.settings.SettingsConstants
import net.pubnative.sdkshowcase.ui.activities.*

/**
 * A placeholder fragment containing a simple view.
 */
class MainFragment : Fragment() {

    private val TAG = MainFragment::class.java!!.getSimpleName()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        demang_config_container.setOnClickListener({ view ->
            DemandConfigActivity.start(context)
        })

        banner_button.setOnClickListener({ view ->
            BannerActivity.start(context)
        })

        feed_small_button.setOnClickListener({ view ->
            FeedSmallActivity.start(context)
        })

        feed_medium_button.setOnClickListener({ view ->
            FeedMediumActivity.start(context)
        })

        interstitial_button.setOnClickListener({ view ->
            InterstitialActivity.start(context)
        })
    }

    override fun onResume() {
        super.onResume()
        checkAvailableDemandTypes()
    }

    fun checkAvailableDemandTypes() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val demandType = preferences.getInt(SettingsConstants.SETTING_DEMMAND_TYPE, SettingsConstants.DEMAND_TYPE_NATIVE)
        val nativeByDefault = preferences.getBoolean(SettingsConstants.SETTING_NATIVE_DEFAULT, true)

        toggleNonVideoButtons(!(demandType == SettingsConstants.DEMAND_TYPE_VIDEO && !nativeByDefault))
    }

    fun toggleNonVideoButtons(enabled: Boolean) {
        feed_small_button.isEnabled = enabled
        banner_button.isEnabled = enabled
    }
}
