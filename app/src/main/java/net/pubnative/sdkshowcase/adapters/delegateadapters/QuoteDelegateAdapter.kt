package net.pubnative.sdkshowcase.adapters.delegateadapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_quote.view.*
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.data.models.Quote
import net.pubnative.sdkshowcase.ui.views.ViewType
import net.pubnative.sdkshowcase.util.inflate


import kotlinx.android.synthetic.main.item_quote.view.*

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class QuoteDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = QuoteViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as QuoteViewHolder
        holder.bind(item as Quote)
    }

    inner class QuoteViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.item_quote)) {

        fun bind(item : Quote) = with(itemView) {
            quote_text.text = item.text
            quote_author.text = item.author
        }
    }
}