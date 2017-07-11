package net.pubnative.sdkshowcase.ui.presenters

import android.content.Context
import android.widget.Toast
import net.pubnative.sdk.layouts.PNLayout
import net.pubnative.sdk.layouts.PNSmallLayout
import net.pubnative.sdkshowcase.APP_TOKEN
import net.pubnative.sdkshowcase.ui.contracts.BannerFragmentContract
import java.lang.Exception

/**
 * Created by erosgarciaponte on 11.07.17.
 */
class StandardBannerPresenter (val context: Context,
                               val view: BannerFragmentContract.View,
                               val placementId: String) : BannerFragmentContract.Presenter,
        PNLayout.TrackListener, PNLayout.LoadListener {

    val banner: PNSmallLayout

    init {
        banner = PNSmallLayout()
    }

    override fun start() {
        banner.setLoadListener(this)
        banner.setTrackListener(this)
        banner.load(context, APP_TOKEN, placementId)
    }

    override fun startTracking() {
        banner.startTrackingView()
    }

    override fun stopTracking() {
        banner.stopTrackingView()
    }

    override fun onPNLayoutTrackClick(layout: PNLayout?) {
        Toast.makeText(context,
                "onPNLayoutTrackClick",
                Toast.LENGTH_LONG).show()
    }

    override fun onPNLayoutTrackImpression(layout: PNLayout?) {
        Toast.makeText(context,
                "onPNLayoutTrackImpression",
                Toast.LENGTH_LONG).show()
    }

    override fun onPNLayoutLoadFail(layout: PNLayout?, error: Exception?) {
        Toast.makeText(context,
                error!!.message ?: "An error occurred while trying to load the ad",
                Toast.LENGTH_LONG).show()
    }

    override fun onPNLayoutLoadFinish(layout: PNLayout?) {
        view.setView(banner.getView(context))
    }
}