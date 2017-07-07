package net.pubnative.sdkshowcase.adapters.delegateadapters.small

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Toast
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubView
import kotlinx.android.synthetic.main.item_mopub_standard_banner.view.*
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.adapters.delegateadapters.ViewTypeDelegateAdapter
import net.pubnative.sdkshowcase.data.models.MoPubStandardBanner
import net.pubnative.sdkshowcase.ui.views.DestroyableView
import net.pubnative.sdkshowcase.ui.views.ViewType
import net.pubnative.sdkshowcase.util.inflate
import java.lang.Exception

/**
 * Created by erosgarciaponte on 07.07.17.
 */
class MoPubStandardBannerDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = MoPubStandardBannerViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        item as MoPubStandardBanner
        (holder as MoPubStandardBannerViewHolder).bind(item)
    }

    inner class MoPubStandardBannerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_mopub_standard_banner)), DestroyableView {

        fun bind(item: MoPubStandardBanner) = with(itemView) {
            mopub_view.adUnitId = item.adUnitId
            mopub_view.bannerAdListener = object : MoPubView.BannerAdListener {
                override fun onBannerClicked(banner: MoPubView?) {
                    Toast.makeText(itemView.context, "onBannerClicked", Toast.LENGTH_LONG).show()
                }

                override fun onBannerCollapsed(banner: MoPubView?) {
                    Toast.makeText(itemView.context, "onBannerCollapsed", Toast.LENGTH_LONG).show()
                }

                override fun onBannerExpanded(banner: MoPubView?) {
                    Toast.makeText(itemView.context, "onBannerExpanded", Toast.LENGTH_LONG).show()
                }

                override fun onBannerFailed(banner: MoPubView?, errorCode: MoPubErrorCode?) {
                    Toast.makeText(itemView.context, "onBannerFailed: ${errorCode.toString()}", Toast.LENGTH_LONG).show()
                }

                override fun onBannerLoaded(banner: MoPubView?) {
                    Toast.makeText(itemView.context, "onBannerLoaded", Toast.LENGTH_LONG).show()
                }
            }
            mopub_view.loadAd()
        }

        override fun destroy() {
            itemView.mopub_view.destroy()
        }
    }
}