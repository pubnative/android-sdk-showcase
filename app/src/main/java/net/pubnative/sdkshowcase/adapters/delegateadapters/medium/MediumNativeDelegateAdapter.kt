package net.pubnative.sdkshowcase.adapters.delegateadapters.medium

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.item_medium_native.view.*
import net.pubnative.sdk.core.request.PNAdModel
import net.pubnative.sdk.core.request.PNRequest
import net.pubnative.sdkshowcase.APP_TOKEN
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.adapters.delegateadapters.ViewTypeDelegateAdapter
import net.pubnative.sdkshowcase.data.models.MediumNativeAd
import net.pubnative.sdkshowcase.ui.views.DestroyableView
import net.pubnative.sdkshowcase.ui.views.ViewType
import net.pubnative.sdkshowcase.util.inflate
import java.lang.Exception

/**
 * Created by erosgarciaponte on 06.07.17.
 */
class MediumNativeDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = MediumNativeViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        item as MediumNativeAd
        (holder as MediumNativeViewHolder).bind(item)
    }

    inner class MediumNativeViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_medium_native)), DestroyableView {

        val mediumRequest: PNRequest
        var mediumAdModel: PNAdModel? = null

        init {
            mediumRequest = PNRequest()
        }

        fun bind(item: MediumNativeAd) = with(itemView) {
            mediumRequest.start(context, APP_TOKEN, item.placementId, object : PNRequest.Listener {
                override fun onPNRequestLoadFail(request: PNRequest?, error: Exception?) {
                    mediumAdModel = null
                    cleanView()
                    Toast.makeText(itemView.context, error!!.message ?: "An error occurred whilst loading the ad", Toast.LENGTH_LONG).show()
                }

                override fun onPNRequestLoadFinish(request: PNRequest?, adModel: PNAdModel?) {
                    if (mediumAdModel != null) {
                        mediumAdModel!!.stopTracking()
                        cleanView()
                    }

                    mediumAdModel = adModel
                    renderAd()
                }
            })
        }

        fun renderAd() {
            if (mediumAdModel != null) {
                mediumAdModel!!
                        .withIcon(itemView.native_icon as ImageView)
                        .withTitle(itemView.native_title as TextView)
                        .withRating(itemView.native_rating as RatingBar)
                        .withBanner(itemView.native_banner as FrameLayout)
                        .withDescription(itemView.native_description as TextView)
                        .withCallToAction(itemView.native_call_to_action as Button)
                        .withContentInfoContainer(itemView.native_disclaimer as FrameLayout)
                        .startTracking(itemView as ViewGroup)
            }
        }

        fun cleanView() {
            (itemView.native_disclaimer as FrameLayout).removeAllViews()
            (itemView.native_title as TextView).text = ""
            (itemView.native_description as TextView).text = ""
            (itemView.native_icon as ImageView).setImageDrawable(null)
            (itemView.native_call_to_action as Button).text = ""
        }

        override fun destroy() {
            mediumAdModel?.stopTracking()
        }
    }
}