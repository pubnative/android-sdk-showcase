package net.pubnative.sdkshowcase.ui.fragments

import net.pubnative.sdkshowcase.ui.contracts.ListFragmentContract
import net.pubnative.sdkshowcase.ui.presenters.FeedSmallPresenter

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class FeedSmallFragment : RecyclerViewFragment() {

    override fun getViewPresenter(): ListFragmentContract.Presenter {
        return FeedSmallPresenter(context, this)
    }
}