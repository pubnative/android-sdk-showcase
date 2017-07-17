package net.pubnative.sdkshowcase.ui.presenters

import android.content.Context
import net.pubnative.sdkshowcase.*
import net.pubnative.sdkshowcase.data.models.MediumAssetGroupAd
import net.pubnative.sdkshowcase.ui.contracts.ListFragmentContract
import net.pubnative.sdkshowcase.ui.views.ViewType

/**
 * Created by erosgarciaponte on 12.07.17.
 */
class FeedMediumPresenter(context: Context, view: ListFragmentContract.View) : ItemFeedPresenter(context, view) {

    override fun addNativeAds(list: ArrayList<ViewType>) {
        list.add(INJECT_PUBNATIVE_AD_POSITION, MediumAssetGroupAd(MEDIUM_NATIVE_PLACEMENT_ID))
    }

    override fun addStandardAds(list: ArrayList<ViewType>) {
        list.add(INJECT_PUBNATIVE_AD_POSITION, MediumAssetGroupAd(MEDIUM_STANDARD_PLACEMENT_ID))
    }

    override fun addVideoAds(list: ArrayList<ViewType>) {
        list.add(INJECT_PUBNATIVE_AD_POSITION, MediumAssetGroupAd(MEDIUM_VIDEO_PLACEMENT_ID))
    }

    override fun addAdTags(list: ArrayList<ViewType>) {
        list.add(INJECT_PUBNATIVE_AD_POSITION, MediumAssetGroupAd(MEDIUM_AD_TAG_PLACEMENT_ID))
    }
}