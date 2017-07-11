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

    override fun addNativeAds(list: ArrayList<ViewType>) {
        list.add(INJECT_PUBNATIVE_AD_POSITION, MediumNativeAd(MEDIUM_PLACEMENT_ID))
    }

    override fun addStandardAds(list: ArrayList<ViewType>) {
        list.add(INJECT_PUBNATIVE_AD_POSITION, MediumStandardAd(MEDIUM_PLACEMENT_ID))
    }

    override fun addVideoAds(list: ArrayList<ViewType>) {
        list.add(INJECT_PUBNATIVE_AD_POSITION, MediumStandardAd(MEDIUM_VIDEO_PLACEMENT_ID))
    }

    override fun addAdTags(list: ArrayList<ViewType>) {
        list.add(INJECT_PUBNATIVE_AD_POSITION, MediumStandardAd(MEDIUM_AD_TAG_PLACEMENT_ID))
    }
}