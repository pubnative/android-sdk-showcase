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
class FeedSmallFragment : RecyclerViewFragment() {

    override fun addNativeAds(list: ArrayList<ViewType>) {
        list.add(INJECT_PUBNATIVE_AD_POSITION, SmallNativeAd(SMALL_PLACEMENT_ID))
    }

    override fun addStandardAds(list: ArrayList<ViewType>) {
        list.add(INJECT_PUBNATIVE_AD_POSITION, SmallStandardAd(SMALL_PLACEMENT_ID))
    }

    override fun addVideoAds(list: ArrayList<ViewType>) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences.contains(SettingsConstants.SETTING_NATIVE_DEFAULT)
                && preferences.getBoolean(SettingsConstants.SETTING_NATIVE_DEFAULT, true)) {
            addNativeAds(list)
        }
    }

    override fun addAdTags(list: ArrayList<ViewType>) {
        list.add(INJECT_PUBNATIVE_AD_POSITION, SmallStandardAd(SMALL_AD_TAG_PLACEMENT_ID))
    }
}