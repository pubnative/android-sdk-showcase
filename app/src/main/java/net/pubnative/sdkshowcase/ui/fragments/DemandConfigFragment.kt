package net.pubnative.sdkshowcase.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.CompoundButton
import kotlinx.android.synthetic.main.fragment_demand_config.*
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.settings.SettingsConstants

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class DemandConfigFragment : Fragment() {
    var preferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_demand_config, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateWithSettings()

        pubnative_config_radio_group.setOnCheckedChangeListener { radioGroup, i ->
            handleSelectionChange(i)
        }

        pubnative_native_default_check.setOnCheckedChangeListener { checkBox, checked ->
            handleNativeByDefaultCheck(checked)
        }
    }

    fun updateWithSettings() {
        val currentChoice = preferences?.getInt(SettingsConstants.SETTING_DEMMAND_TYPE, SettingsConstants.DEMAND_TYPE_NATIVE)

        when (currentChoice) {
            SettingsConstants.DEMAND_TYPE_NATIVE -> {
                chooseRadioItem(R.id.pubnative_native_radio)
            }
            SettingsConstants.DEMAND_TYPE_STANDARD -> {
                chooseRadioItem(R.id.pubnative_standard_radio)
            }
            SettingsConstants.DEMAND_TYPE_VIDEO -> {
                chooseRadioItem(R.id.pubnative_video_radio)
            }
            SettingsConstants.DEMAND_TYPE_AD_TAG -> {
                chooseRadioItem(R.id.pubnative_adtag_radio)
            }
            else -> {
                chooseRadioItem(R.id.pubnative_native_radio)
            }
        }

        val nativeByDefault = preferences?.getBoolean(SettingsConstants.SETTING_NATIVE_DEFAULT, true)

        checkNativeByDefault(nativeByDefault!!)
    }

    fun chooseRadioItem(id: Int) {
        pubnative_config_radio_group.post {
            pubnative_config_radio_group.check(id)
        }
    }

    fun checkNativeByDefault(checked: Boolean) {
        pubnative_native_default_check.post {
            pubnative_native_default_check.isChecked = checked
        }
    }

    fun handleSelectionChange(id: Int) {
        var selection = SettingsConstants.DEMAND_TYPE_NATIVE
        when (id) {
            R.id.pubnative_native_radio -> {
                selection = SettingsConstants.DEMAND_TYPE_NATIVE
            }
            R.id.pubnative_standard_radio -> {
                selection = SettingsConstants.DEMAND_TYPE_STANDARD
            }
            R.id.pubnative_video_radio -> {
                selection = SettingsConstants.DEMAND_TYPE_VIDEO
            }
            R.id.pubnative_adtag_radio -> {
                selection = SettingsConstants.DEMAND_TYPE_AD_TAG
            }
        }
        preferences?.edit()?.putInt(SettingsConstants.SETTING_DEMMAND_TYPE, selection)?.apply()
    }

    fun handleNativeByDefaultCheck(checked: Boolean) {
        preferences?.edit()?.putBoolean(SettingsConstants.SETTING_NATIVE_DEFAULT, checked)?.apply()
    }
}