package net.pubnative.sdkshowcase.adapters.delegateadapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.ui.views.ViewType
import net.pubnative.sdkshowcase.util.inflate

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class QuoteDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class QuoteViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_quote)) {

    }
}