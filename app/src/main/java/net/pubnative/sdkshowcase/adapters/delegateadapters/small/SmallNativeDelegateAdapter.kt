package net.pubnative.sdkshowcase.adapters.delegateadapters.small

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.item_small_native.view.*
import kotlinx.android.synthetic.main.layout_small_native.view.*
import net.pubnative.sdk.core.request.PNAdModel
import net.pubnative.sdk.core.request.PNRequest
import net.pubnative.sdkshowcase.APP_TOKEN
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.adapters.delegateadapters.ViewTypeDelegateAdapter
import net.pubnative.sdkshowcase.data.models.SmallNativeAd
import net.pubnative.sdkshowcase.ui.views.DestroyableView
import net.pubnative.sdkshowcase.ui.views.ViewType
import net.pubnative.sdkshowcase.util.inflate
import java.lang.Exception

/**
 * Created by erosgarciaponte on 06.07.17.
 */
class SmallNativeDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = SmallNativeViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        item as SmallNativeAd
        (holder as SmallNativeViewHolder).bind(item)
    }

    inner class SmallNativeViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_small_native)), DestroyableView {

        val smallRequest: PNRequest
        var smallAdModel: PNAdModel? = null
        var nativeAdView: ViewGroup? = null

        init {
            smallRequest = PNRequest()
        }

        fun bind(item: SmallNativeAd) = with(itemView) {
            smallRequest.start(context, APP_TOKEN, item.placementId, object : PNRequest.Listener {
                override fun onPNRequestLoadFail(request: PNRequest?, error: Exception?) {
                    smallAdModel = null
                    cleanView()
                    Toast.makeText(itemView.context, error!!.message ?: "An error occurred whilst loading the ad", Toast.LENGTH_LONG).show()
                }

                override fun onPNRequestLoadFinish(request: PNRequest?, adModel: PNAdModel?) {
                    if (smallAdModel != null) {
                        smallAdModel!!.stopTracking()
                        cleanView()
                    }

                    smallAdModel = adModel
                    renderAd()
                }
            })
        }

        fun renderAd() {
            if (smallAdModel != null) {
                nativeAdView = LayoutInflater.from(itemView.context).inflate(R.layout.layout_small_native, null, false) as ViewGroup
                itemView.ad_container.addView(nativeAdView)
                itemView.visibility = View.VISIBLE

                smallAdModel!!
                        .withIcon(nativeAdView?.native_icon as ImageView)
                        .withTitle(nativeAdView?.native_title as TextView)
                        .withDescription(nativeAdView?.native_description as TextView)
                        .withCallToAction(nativeAdView?.native_call_to_action as Button)
                        .withContentInfoContainer(nativeAdView?.native_disclaimer as FrameLayout)
                        .startTracking(nativeAdView as ViewGroup)
            }
        }

        fun cleanView() {
            itemView.ad_container.removeAllViews()
        }

        override fun destroy() {
            smallAdModel?.stopTracking()
        }
    }
}