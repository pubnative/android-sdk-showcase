package net.pubnative.sdkshowcase.adapters

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.ViewGroup
import net.pubnative.sdkshowcase.adapters.delegateadapters.QuoteDelegateAdapter
import net.pubnative.sdkshowcase.adapters.delegateadapters.ViewTypeDelegateAdapter
import net.pubnative.sdkshowcase.adapters.delegateadapters.medium.MediumNativeDelegateAdapter
import net.pubnative.sdkshowcase.adapters.delegateadapters.medium.MediumStandardDelegateAdapter
import net.pubnative.sdkshowcase.adapters.delegateadapters.small.*
import net.pubnative.sdkshowcase.data.models.Quote
import net.pubnative.sdkshowcase.ui.views.DestroyableView
import net.pubnative.sdkshowcase.ui.views.ViewType

/**
 * Created by erosgarciaponte on 06.07.17.
 */
class ItemsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items : ArrayList<ViewType>
    private var delegateAdapters : SparseArray<ViewTypeDelegateAdapter>

    init {
        items = ArrayList<ViewType>()
        delegateAdapters = SparseArray<ViewTypeDelegateAdapter>()

        delegateAdapters.put(AdapterConstants.QUOTE, QuoteDelegateAdapter())

        delegateAdapters.put(AdapterConstants.AD_SMALL_NATIVE, SmallNativeDelegateAdapter())
        delegateAdapters.put(AdapterConstants.AD_SMALL_STANDARD, SmallStandardDelegateAdapter())

        delegateAdapters.put(AdapterConstants.AD_MEDIUM_NATIVE, MediumNativeDelegateAdapter())
        delegateAdapters.put(AdapterConstants.AD_MEDIUM_STANDARD, MediumStandardDelegateAdapter())
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val adapter = delegateAdapters.get(viewType)
        val viewHolder = adapter.onCreateViewHolder(parent!!)
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder!!, this.items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return this.items[position].getViewType()
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder?) {
        super.onViewDetachedFromWindow(holder)
        if (holder is DestroyableView) {
            holder.destroy()
        }
    }

    fun addQuotes(quotes: List<ViewType>) {
        items.addAll(quotes)
        notifyDataSetChanged()
    }

    fun getQuotes(): List<Quote> {
        return items
                .filter { it.getViewType() == AdapterConstants.QUOTE }
                .map { it as Quote }
    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex
}