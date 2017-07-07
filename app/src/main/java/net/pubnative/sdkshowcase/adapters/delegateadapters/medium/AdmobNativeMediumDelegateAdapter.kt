package net.pubnative.sdkshowcase.adapters.delegateadapters.medium

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.NativeExpressAdView
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.adapters.delegateadapters.ViewTypeDelegateAdapter
import net.pubnative.sdkshowcase.data.models.AdmobNativeBanner
import net.pubnative.sdkshowcase.data.models.AdmobNativeMedium
import net.pubnative.sdkshowcase.ui.views.ViewType
import net.pubnative.sdkshowcase.util.inflate

/**
 * Created by erosgarciaponte on 07.07.17.
 */
class AdmobNativeMediumDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = AdmobNativeMediumViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        item as AdmobNativeMedium
        (holder as AdmobNativeMediumViewHolder).bind(item)
    }

    inner class AdmobNativeMediumViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_admob)) {

        fun bind(item: AdmobNativeMedium) = with(itemView) {
            this as ViewGroup
            this.removeAllViews()

            val adView = NativeExpressAdView(context)
            adView.adSize = AdSize(280, 132)
            adView.adUnitId = item.adUnitId
            this.addView(adView)

            loadAd(adView)
        }

        fun loadAd(adView: NativeExpressAdView) {
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