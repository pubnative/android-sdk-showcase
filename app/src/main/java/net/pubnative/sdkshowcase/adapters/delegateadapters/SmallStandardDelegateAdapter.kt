package net.pubnative.sdkshowcase.adapters.delegateadapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.item_small_standard.view.*
import net.pubnative.sdk.layouts.PNLayout
import net.pubnative.sdk.layouts.PNSmallLayout
import net.pubnative.sdkshowcase.APP_TOKEN
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.data.models.SmallAssetGroupAd
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
        item as SmallAssetGroupAd
        (holder as SmallStandardViewHolder).bind(item)
    }

    inner class SmallStandardViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_small_standard)), DestroyableView, PNLayout.LoadListener, PNLayout.TrackListener {
        private val TAG = SmallStandardViewHolder::class.java.simpleName

        val smallLayout: PNSmallLayout

        init {
            smallLayout = PNSmallLayout()
            smallLayout.setLoadListener(this)
            smallLayout.setTrackListener(this)
        }

        fun bind(item: SmallAssetGroupAd) = with(itemView) {
            smallLayout.load(context, APP_TOKEN, item.placementId)
        }

        override fun destroy() {
            smallLayout.stopTrackingView()
        }

        override fun onPNLayoutLoadFail(layout: PNLayout?, error: Exception?) {
            Log.d(TAG, error?.message?: "An error occurred whilst loading the ad")
            Toast.makeText(itemView.context, error?.message?: "An error occurred whilst loading the ad", Toast.LENGTH_LONG).show()
            itemView.load_ad_progress.visibility = View.GONE
        }

        override fun onPNLayoutLoadFinish(layout: PNLayout?) {
            smallLayout.startTrackingView()
            (itemView.ad_container as FrameLayout).addView(smallLayout.getView(itemView.context))
            itemView.load_ad_progress.visibility = View.GONE
        }

        override fun onPNLayoutTrackClick(layout: PNLayout?) {
            Log.d(TAG, "onPNLayoutTrackClick")
        }

        override fun onPNLayoutTrackImpression(layout: PNLayout?) {
            Log.d(TAG, "onPNLayoutTrackImpression")
        }
    }
}