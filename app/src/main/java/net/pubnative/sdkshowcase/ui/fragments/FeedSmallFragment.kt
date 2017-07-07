package net.pubnative.sdkshowcase.ui.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.pubnative.sdkshowcase.*
import net.pubnative.sdkshowcase.data.models.MoPubNativeBanner
import net.pubnative.sdkshowcase.data.models.MoPubStandardBanner
import net.pubnative.sdkshowcase.data.models.SmallNativeAd
import net.pubnative.sdkshowcase.data.models.SmallStandardAd
import net.pubnative.sdkshowcase.settings.SettingsConstants
import net.pubnative.sdkshowcase.ui.views.ViewType

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class FeedSmallFragment : RecyclerViewFragment() {

    override fun injectAds(quotes: ArrayList<ViewType>) {
        super.injectAds(quotes)
        addDemandTypeAds(quotes)
    }

    fun getDemandTypeAd(): ViewType {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences.contains(SettingsConstants.SETTING_DEMMAND_TYPE)) {
            when (preferences.getInt(SettingsConstants.SETTING_DEMMAND_TYPE, SettingsConstants.DEMAND_TYPE_NATIVE)) {
                SettingsConstants.DEMAND_TYPE_NATIVE -> return SmallNativeAd(SMALL_PLACEMENT_ID)
                SettingsConstants.DEMAND_TYPE_STANDARD -> return SmallStandardAd(SMALL_PLACEMENT_ID)
                SettingsConstants.DEMAND_TYPE_VIDEO -> return SmallNativeAd(SMALL_PLACEMENT_ID)
                SettingsConstants.DEMAND_TYPE_AD_TAG -> return SmallNativeAd(SMALL_PLACEMENT_ID)
                else -> return SmallNativeAd(SMALL_PLACEMENT_ID)
            }
        } else {
            return SmallNativeAd(SMALL_PLACEMENT_ID)
        }
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
        list.add(INJECT_PUBNATIVE_AD_POSITION, SmallNativeAd(SMALL_PLACEMENT_ID))
        //list.add(INJECT_MOPUB_AD_POSITION, MoPubNativeBanner(MOPUB_NATIVE_AD_UNIT_ID))
    }

    fun addStandardAds(list: ArrayList<ViewType>) {
        list.add(INJECT_PUBNATIVE_AD_POSITION, SmallStandardAd(SMALL_PLACEMENT_ID))
        list.add(INJECT_MOPUB_AD_POSITION, MoPubStandardBanner(MOPUB_BANNER_AD_UNIT_ID))
    }
}