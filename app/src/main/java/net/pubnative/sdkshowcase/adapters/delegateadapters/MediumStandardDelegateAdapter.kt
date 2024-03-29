package net.pubnative.sdkshowcase.adapters.delegateadapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.item_medium_standard.view.*
import net.pubnative.sdk.layouts.PNLayout
import net.pubnative.sdk.layouts.PNMediumLayout
import net.pubnative.sdkshowcase.APP_TOKEN
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.data.models.MediumAssetGroupAd
import net.pubnative.sdkshowcase.ui.views.DestroyableView
import net.pubnative.sdkshowcase.ui.views.ViewType
import net.pubnative.sdkshowcase.util.inflate
import java.lang.Exception

/**
 * Created by erosgarciaponte on 06.07.17.
 */
class MediumStandardDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = MediumStandardViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        item as MediumAssetGroupAd
        (holder as MediumStandardViewHolder).bind(item)
    }

    inner class MediumStandardViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_medium_standard)), DestroyableView, PNLayout.LoadListener, PNLayout.TrackListener {

        private val TAG = MediumStandardViewHolder::class.java.simpleName

        val mediumLayout: PNMediumLayout

        init {
            mediumLayout = PNMediumLayout()
            mediumLayout.setLoadListener(this)
            mediumLayout.setTrackListener(this)
        }

        fun bind(item: MediumAssetGroupAd) = with(itemView) {
            mediumLayout.load(context, APP_TOKEN, item.placementId)
        }

        override fun destroy() {
            mediumLayout.stopTrackingView()
        }

        override fun onPNLayoutLoadFail(layout: PNLayout?, error: Exception?) {
            Log.d(TAG, error?.message?: "An error occurred whilst loading the ad")
            Toast.makeText(itemView.context, error?.message?: "An error occurred whilst loading the ad", Toast.LENGTH_LONG).show()
            itemView.load_ad_progress.visibility = View.GONE
        }

        override fun onPNLayoutLoadFinish(layout: PNLayout?) {
            mediumLayout.startTrackingView()
            (itemView.ad_container as FrameLayout).addView(mediumLayout.getView(itemView.context))
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