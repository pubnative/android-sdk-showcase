package net.pubnative.sdkshowcase.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import net.pubnative.sdkshowcase.R


import kotlinx.android.synthetic.main.fragment_recycler.*
import net.pubnative.sdkshowcase.adapters.ItemsAdapter
import net.pubnative.sdkshowcase.data.QuotesRepository
import net.pubnative.sdkshowcase.data.models.Quote
import net.pubnative.sdkshowcase.data.source.DataSource
import net.pubnative.sdkshowcase.ui.views.ViewType

/**
 * Created by erosgarciaponte on 06.07.17.
 */
open class RecyclerViewFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_recycler, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_view.apply {
            setHasFixedSize(false)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
        }

        initAdapter()

        requestQuotes()
    }

    fun initAdapter() {
        if (recycler_view.adapter == null) {
            recycler_view.adapter = ItemsAdapter()
        }
    }

    fun requestQuotes() {
        val quotesRepository = QuotesRepository(context)
        quotesRepository.getAll(object : DataSource.Callback<Quote> {
            override fun onSuccess(list: List<Quote>) {
                val adapterList = ArrayList<ViewType>()
                adapterList.addAll(list)
                injectAds(adapterList)
                (recycler_view.adapter as ItemsAdapter).addQuotes(adapterList)
            }

            override fun onError(throwable: Throwable) {
                Toast.makeText(context, throwable.message ?: "Error fetching quotes", Toast.LENGTH_LONG).show()
            }
        })
    }

    /**
     * Inject ads into list in child classes
     */
    open fun injectAds(quotes : List<ViewType>) {}

}