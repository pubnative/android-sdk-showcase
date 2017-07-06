package net.pubnative.sdkshowcase.data.models

import net.pubnative.sdkshowcase.adapters.AdapterConstants
import net.pubnative.sdkshowcase.ui.views.ViewType

/**
 * Created by erosgarciaponte on 05.07.17.
 */
data class Quote(
        val text: String,
        val author: String) : ViewType {
    override fun getViewType(): Int = AdapterConstants.QUOTE
}