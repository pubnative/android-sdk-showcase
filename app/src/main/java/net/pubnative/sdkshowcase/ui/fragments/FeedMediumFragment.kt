package net.pubnative.sdkshowcase.ui.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.pubnative.sdkshowcase.*
import net.pubnative.sdkshowcase.data.models.*
import net.pubnative.sdkshowcase.settings.SettingsConstants
import net.pubnative.sdkshowcase.ui.views.ViewType

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class FeedMediumFragment : RecyclerViewFragment() {

    override fun injectAds(quotes: ArrayList<ViewType>) {
        super.injectAds(quotes)
        addDemandTypeAds(quotes)

    }

    fun addDemandTypeAds(quotes: ArrayList<ViewType>) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences.contains(SettingsConstants.SETTING_DEMMAND_TYPE)) {
            when (preferences.getInt(SettingsConstants.SETTING_DEMMAND_TYPE, SettingsConstants.DEMAND_TYPE_NATIVE)) {
                SettingsConstants.DEMAND_TYPE_NATIVE -> {
                    addNativeAds(quotes)
                }
                SettingsConstants.DEMAND_TYPE_STANDARD -> {
                    addStandardAds(quotes)
                }
                SettingsConstants.DEMAND_TYPE_VIDEO -> {
                    addNativeAds(quotes)
                }
                SettingsConstants.DEMAND_TYPE_AD_TAG -> {
                    addNativeAds(quotes)
                }
                else -> {
                    addNativeAds(quotes)
                }
            }
        } else {
            addNativeAds(quotes)
        }
    }

    fun addNativeAds(list: ArrayList<ViewType>) {
        list.add(INJECT_PUBNATIVE_AD_POSITION, MediumNativeAd(MEDIUM_PLACEMENT_ID))
        //list.add(INJECT_MOPUB_AD_POSITION, MoPubNativeMedium(MOPUB_NATIVE_AD_UNIT_ID))
        list.add(INJECT_ADMOB_AD_POSITION, AdmobNativeMedium(ADMOB_NATIVE_MEDIUM_AD_UNIT_ID))
    }

    fun addStandardAds(list: ArrayList<ViewType>) {
        list.add(INJECT_PUBNATIVE_AD_POSITION, MediumStandardAd(MEDIUM_PLACEMENT_ID))
        list.add(INJECT_MOPUB_AD_POSITION, MoPubStandardMedium(MOPUB_MEDIUM_AD_UNIT_ID))
    }
}