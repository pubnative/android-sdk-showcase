package net.pubnative.sdkshowcase.ui.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.pubnative.sdkshowcase.INJECT_AD_POSITION
import net.pubnative.sdkshowcase.MEDIUM_PLACEMENT_ID
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.data.models.MediumNativeAd
import net.pubnative.sdkshowcase.data.models.MediumStandardAd
import net.pubnative.sdkshowcase.settings.SettingsConstants
import net.pubnative.sdkshowcase.ui.views.ViewType

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class FeedMediumFragment : RecyclerViewFragment() {

    override fun injectAds(quotes: ArrayList<ViewType>) {
        super.injectAds(quotes)
        quotes.add(INJECT_AD_POSITION, getDemandTypeAd())
    }

    fun getDemandTypeAd(): ViewType {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences.contains(SettingsConstants.SETTING_DEMMAND_TYPE)) {
            when (preferences.getInt(SettingsConstants.SETTING_DEMMAND_TYPE, SettingsConstants.DEMAND_TYPE_NATIVE)) {
                SettingsConstants.DEMAND_TYPE_NATIVE -> return MediumNativeAd(MEDIUM_PLACEMENT_ID)
                SettingsConstants.DEMAND_TYPE_STANDARD -> return MediumStandardAd(MEDIUM_PLACEMENT_ID)
                SettingsConstants.DEMAND_TYPE_VIDEO -> return MediumNativeAd(MEDIUM_PLACEMENT_ID)
                SettingsConstants.DEMAND_TYPE_AD_TAG -> return MediumNativeAd(MEDIUM_PLACEMENT_ID)
                else -> return MediumNativeAd(MEDIUM_PLACEMENT_ID)
            }
        } else {
            return MediumNativeAd(MEDIUM_PLACEMENT_ID)
        }
    }
}