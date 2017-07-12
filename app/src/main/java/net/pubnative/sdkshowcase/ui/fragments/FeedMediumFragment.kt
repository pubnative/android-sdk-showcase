package net.pubnative.sdkshowcase.ui.fragments

import net.pubnative.sdkshowcase.ui.contracts.ListFragmentContract
import net.pubnative.sdkshowcase.ui.presenters.FeedMediumPresenter

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class FeedMediumFragment : RecyclerViewFragment() {

    override fun getViewPresenter(): ListFragmentContract.Presenter = FeedMediumPresenter(context, this)
}