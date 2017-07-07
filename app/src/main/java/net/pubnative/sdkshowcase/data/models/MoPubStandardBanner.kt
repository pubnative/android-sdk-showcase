package net.pubnative.sdkshowcase.data.models

import net.pubnative.sdkshowcase.adapters.AdapterConstants
import net.pubnative.sdkshowcase.ui.views.ViewType

/**
 * Created by erosgarciaponte on 07.07.17.
 */
class MoPubStandardBanner(val adUnitId: String) : ViewType {
    override fun getViewType(): Int = AdapterConstants.MOPUB_BANNER_STANDARD
}