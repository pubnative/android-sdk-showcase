package net.pubnative.sdkshowcase.adapters.delegateadapters.medium

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.adapters.delegateadapters.ViewTypeDelegateAdapter
import net.pubnative.sdkshowcase.data.models.MediumStandardAd
import net.pubnative.sdkshowcase.ui.views.DestroyableView
import net.pubnative.sdkshowcase.ui.views.ViewType
import net.pubnative.sdkshowcase.util.inflate

/**
 * Created by erosgarciaponte on 06.07.17.
 */
class MediumStandardDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = MediumStandardViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        item as MediumStandardAd
        (holder as MediumStandardViewHolder).bind(item)
    }

    inner class MediumStandardViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_medium_standard)), DestroyableView {

        fun bind(item: MediumStandardAd) = with(itemView) {

        }

        override fun destroy() {

        }
    }
}