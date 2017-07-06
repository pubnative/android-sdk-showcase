package net.pubnative.sdkshowcase.adapters.delegateadapters.small

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.adapters.delegateadapters.ViewTypeDelegateAdapter
import net.pubnative.sdkshowcase.data.models.SmallStandardAd
import net.pubnative.sdkshowcase.ui.views.DestroyableView
import net.pubnative.sdkshowcase.ui.views.ViewType
import net.pubnative.sdkshowcase.util.inflate

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
            parent.inflate(R.layout.item_small_standard)), DestroyableView {

        fun bind(item: SmallStandardAd) = with(itemView) {

        }

        override fun destroy() {

        }
    }
}