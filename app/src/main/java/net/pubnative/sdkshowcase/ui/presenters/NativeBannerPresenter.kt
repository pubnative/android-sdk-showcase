package net.pubnative.sdkshowcase.ui.presenters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.layout_small_native.view.*
import net.pubnative.sdk.core.request.PNAdModel
import net.pubnative.sdk.core.request.PNRequest
import net.pubnative.sdkshowcase.APP_TOKEN
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.ui.contracts.BannerFragmentContract
import java.lang.Exception

/**
 * Created by erosgarciaponte on 11.07.17.
 */
class NativeBannerPresenter(val context: Context,
                            val view: BannerFragmentContract.View,
                            val placementId: String) : BannerFragmentContract.Presenter, PNRequest.Listener, PNAdModel.Listener {

    val request: PNRequest
    var nativeAdModel: PNAdModel? = null
    var nativeAdView: ViewGroup? = null

    init {
        request = PNRequest()
    }

    override fun start() {
        request.start(context, APP_TOKEN, placementId, this)
    }

    override fun startTracking() {
        nativeAdModel?.startTracking(nativeAdView)
    }

    override fun stopTracking() {
        nativeAdModel?.stopTracking()
    }

    override fun onPNRequestLoadFail(request: PNRequest?, error: Exception?) {
        Toast.makeText(context,
                error?.message ?: "An error occurred while trying to load the ad",
                Toast.LENGTH_LONG).show()
    }

    override fun onPNRequestLoadFinish(request: PNRequest?, adModel: PNAdModel?) {
        nativeAdModel = adModel
        adModel?.setListener(this)

        nativeAdView = LayoutInflater.from(context).inflate(R.layout.layout_small_native, null, false) as ViewGroup
        view.setView(nativeAdView!!)

        nativeAdModel?.withIcon(nativeAdView?.native_icon as ImageView)
                ?.withTitle(nativeAdView?.native_title as TextView)
                ?.withDescription(nativeAdView?.native_description as TextView)
                ?.withCallToAction(nativeAdView?.native_call_to_action as Button)
                ?.withContentInfoContainer(nativeAdView?.native_disclaimer as FrameLayout)
    }

    override fun onPNAdClick(adModel: PNAdModel?) {
        Toast.makeText(context,
                "onPNAdClick",
                Toast.LENGTH_LONG).show()
    }

    override fun onPNAdImpression(adModel: PNAdModel?) {
        Toast.makeText(context,
                "onPNAdImpression",
                Toast.LENGTH_LONG).show()
    }
}