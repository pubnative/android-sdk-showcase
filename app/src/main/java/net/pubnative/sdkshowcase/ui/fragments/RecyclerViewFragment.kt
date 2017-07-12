package net.pubnative.sdkshowcase.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import net.pubnative.sdkshowcase.R


import kotlinx.android.synthetic.main.fragment_recycler.*
import net.pubnative.sdkshowcase.adapters.ItemsAdapter
import net.pubnative.sdkshowcase.ui.contracts.ListFragmentContract
import net.pubnative.sdkshowcase.ui.presenters.ItemFeedPresenter
import net.pubnative.sdkshowcase.ui.views.ViewType

/**
 * Created by erosgarciaponte on 06.07.17.
 */
open class RecyclerViewFragment : Fragment(), ListFragmentContract.View {
    var presenter: ListFragmentContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = getViewPresenter()
    }

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

        presenter?.loadList()
    }

    fun initAdapter() {
        if (recycler_view.adapter == null) {
            recycler_view.adapter = ItemsAdapter()
        }
    }

    open fun getViewPresenter() : ListFragmentContract.Presenter {
        return ItemFeedPresenter(context, this)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(context, throwable.message ?: "Error fetching quotes", Toast.LENGTH_LONG).show()
    }

    override fun showItems(items: ArrayList<ViewType>) {
        (recycler_view.adapter as ItemsAdapter).addQuotes(items)
    }
}