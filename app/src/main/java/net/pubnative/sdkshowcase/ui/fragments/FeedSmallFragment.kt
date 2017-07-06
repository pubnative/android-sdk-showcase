package net.pubnative.sdkshowcase.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.ui.views.ViewType

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class FeedSmallFragment : RecyclerViewFragment() {

    override fun injectAds(quotes: List<ViewType>) {
        super.injectAds(quotes)
    }
}