package net.pubnative.sdkshowcase.ui.presenters

import android.content.Context
import android.preference.PreferenceManager
import net.pubnative.sdkshowcase.*
import net.pubnative.sdkshowcase.data.models.SmallAssetGroupAd
import net.pubnative.sdkshowcase.settings.SettingsConstants
import net.pubnative.sdkshowcase.ui.contracts.ListFragmentContract
import net.pubnative.sdkshowcase.ui.views.ViewType

/**
 * Created by erosgarciaponte on 12.07.17.
 */

class FeedSmallPresenter(context: Context, view: ListFragmentContract.View) : ItemFeedPresenter(context, view) {

    override fun addNativeAds(list: ArrayList<ViewType>) {
        list.add(INJECT_PUBNATIVE_AD_POSITION, SmallAssetGroupAd(SMALL_NATIVE_PLACEMENT_ID))
    }

    override fun addStandardAds(list: ArrayList<ViewType>) {
        list.add(INJECT_PUBNATIVE_AD_POSITION, SmallAssetGroupAd(SMALL_STANDARD_PLACEMENT_ID))
    }

    override fun addVideoAds(list: ArrayList<ViewType>) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences.contains(SettingsConstants.SETTING_NATIVE_DEFAULT)
                && preferences.getBoolean(SettingsConstants.SETTING_NATIVE_DEFAULT, true)) {
            addNativeAds(list)
        }
    }

    override fun addAdTags(list: ArrayList<ViewType>) {
        list.add(INJECT_PUBNATIVE_AD_POSITION, SmallAssetGroupAd(SMALL_AD_TAG_PLACEMENT_ID))
    }
}