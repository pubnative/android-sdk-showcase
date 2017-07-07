package net.pubnative.sdkshowcase.adapters.delegateadapters.small

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.adapters.delegateadapters.ViewTypeDelegateAdapter
import net.pubnative.sdkshowcase.data.models.AdmobStandardBanner
import net.pubnative.sdkshowcase.ui.views.DestroyableView
import net.pubnative.sdkshowcase.ui.views.ViewType
import net.pubnative.sdkshowcase.util.inflate

/**
 * Created by erosgarciaponte on 07.07.17.
 */
class AdmobStandardBannerDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = AdmobStandardBannerViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        item as AdmobStandardBanner
        (holder as AdmobStandardBannerViewHolder).bind(item)
    }

    inner class AdmobStandardBannerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_admob)) {

        fun bind(item: AdmobStandardBanner) = with(itemView) {
            this as ViewGroup
            this.removeAllViews()

            val adView = AdView(context)
            adView.adSize = AdSize.BANNER
            adView.adUnitId = item.adUnitId
            this.addView(adView)

            loadAd(adView)
        }

        fun loadAd(adView: AdView) {
            adView.adListener = object : AdListener() {
                override fun onAdClicked() {
                    Toast.makeText(itemView.context, "onAdClicked", Toast.LENGTH_LONG).show()
                }

                override fun onAdClosed() {
                    Toast.makeText(itemView.context, "onAdClosed", Toast.LENGTH_LONG).show()
                }

                override fun onAdFailedToLoad(errorCode: Int) {
                    Toast.makeText(itemView.context, "onAdFailedToLoad: ${errorCode}", Toast.LENGTH_LONG).show()
                }

                override fun onAdImpression() {
                    Toast.makeText(itemView.context, "onAdImpression", Toast.LENGTH_LONG).show()
                }

                override fun onAdLoaded() {
                    Toast.makeText(itemView.context, "onAdLoaded", Toast.LENGTH_LONG).show()
                }

                override fun onAdOpened() {
                    Toast.makeText(itemView.context, "onAdOpened", Toast.LENGTH_LONG).show()
                }

                override fun onAdLeftApplication() {
                    Toast.makeText(itemView.context, "onAdLeftApplication", Toast.LENGTH_LONG).show()
                }
            }
            val request = AdRequest.Builder().build()
            adView.loadAd(request)
        }
    }
}