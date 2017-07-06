package net.pubnative.sdkshowcase.adapters.delegateadapters.small

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import net.pubnative.sdk.layouts.PNLayout
import net.pubnative.sdk.layouts.PNSmallLayout
import net.pubnative.sdkshowcase.APP_TOKEN
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.adapters.delegateadapters.ViewTypeDelegateAdapter
import net.pubnative.sdkshowcase.data.models.SmallStandardAd
import net.pubnative.sdkshowcase.ui.views.DestroyableView
import net.pubnative.sdkshowcase.ui.views.ViewType
import net.pubnative.sdkshowcase.util.inflate
import java.lang.Exception

/**
 * Created by erosgarciaponte on 06.07.17.
 */
class SmallStandardDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = SmallStandardViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        item as SmallStandardAd
        (holder as SmallStandardViewHolder).bind(item)
    }

    inner class SmallStandardViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_small_standard)), DestroyableView, PNLayout.LoadListener, PNLayout.TrackListener {

        val smallLayout: PNSmallLayout

        init {
            smallLayout = PNSmallLayout()
            smallLayout.setLoadListener(this)
            smallLayout.setTrackListener(this)
        }

        fun bind(item: SmallStandardAd) = with(itemView) {
            smallLayout.load(context, APP_TOKEN, item.placementId)
        }

        override fun destroy() {
            smallLayout.stopTrackingView()
        }

        override fun onPNLayoutLoadFail(layout: PNLayout?, error: Exception?) {
            Toast.makeText(itemView.context, error!!.message?: "An error occurred whilst loading the ad", Toast.LENGTH_LONG).show()
        }

        override fun onPNLayoutLoadFinish(layout: PNLayout?) {
            smallLayout.startTrackingView()
            (itemView as FrameLayout).addView(smallLayout.getView(itemView.context))
        }

        override fun onPNLayoutTrackClick(layout: PNLayout?) {
            Toast.makeText(itemView.context, "onPNLayoutTrackClick", Toast.LENGTH_LONG).show()
        }

        override fun onPNLayoutTrackImpression(layout: PNLayout?) {
            Toast.makeText(itemView.context, "onPNLayoutTrackImpression", Toast.LENGTH_LONG).show()
        }
    }
}