package net.pubnative.sdkshowcase.data.models

import net.pubnative.sdkshowcase.adapters.AdapterConstants
import net.pubnative.sdkshowcase.ui.views.ViewType

/**
 * Created by erosgarciaponte on 06.07.17.
 */
class MediumNativeAd(val placementId: String) : ViewType {
    override fun getViewType(): Int = AdapterConstants.AD_MEDIUM_NATIVE
}