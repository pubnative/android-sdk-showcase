package net.pubnative.sdkshowcase.ui.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.pubnative.sdkshowcase.INJECT_AD_POSITION
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.SMALL_PLACEMENT_ID
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
        quotes.add(INJECT_AD_POSITION, getDemandTypeAd())
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
}