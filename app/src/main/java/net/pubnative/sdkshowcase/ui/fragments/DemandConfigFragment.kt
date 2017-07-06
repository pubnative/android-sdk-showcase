package net.pubnative.sdkshowcase.ui.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_demand_config.*
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.settings.SettingsConstants

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class DemandConfigFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_demand_config, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateWithSettings()
    }

    fun updateWithSettings() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences.contains(SettingsConstants.SETTING_DEMMAND_TYPE)) {
            val currentChoice = preferences.getInt(SettingsConstants.SETTING_DEMMAND_TYPE, 1)

            when (currentChoice) {
                SettingsConstants.DEMAND_TYPE_NATIVE -> {
                    chooseItem(R.id.pubnative_native_radio)
                }
                SettingsConstants.DEMAND_TYPE_STANDARD -> {
                    chooseItem(R.id.pubnative_standard_radio)
                }
                SettingsConstants.DEMAND_TYPE_VIDEO -> {
                    chooseItem(R.id.pubnative_video_radio)
                }
                SettingsConstants.DEMAND_TYPE_AD_TAG -> {
                    chooseItem(R.id.pubnative_adtag_radio)
                }
                else -> {
                    chooseItem(R.id.pubnative_native_radio)
                }
            }
        }
    }

    fun chooseItem(id: Int) {
        pubnative_config_radio_group.post {
            pubnative_config_radio_group.check(id)
        }
    }
}