package net.pubnative.sdkshowcase.ui.presenters

import android.content.Context
import android.preference.PreferenceManager
import net.pubnative.sdkshowcase.data.QuotesRepository
import net.pubnative.sdkshowcase.data.models.Quote
import net.pubnative.sdkshowcase.data.source.DataSource
import net.pubnative.sdkshowcase.settings.SettingsConstants
import net.pubnative.sdkshowcase.ui.contracts.ListFragmentContract
import net.pubnative.sdkshowcase.ui.views.ViewType

/**
 * Created by erosgarciaponte on 12.07.17.
 */
open class ItemFeedPresenter(val context: Context,
                             val view: ListFragmentContract.View) : ListFragmentContract.Presenter, DataSource.Callback<Quote> {
    val quotesRepository : QuotesRepository

    init {
        quotesRepository = QuotesRepository(context)
    }

    override fun loadList() {
        quotesRepository.getAll(this)
    }

    override fun injectItems(list: ArrayList<ViewType>) {
        addDemandTypeAds(list)
    }

    fun addDemandTypeAds(quotes: ArrayList<ViewType>) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences.contains(SettingsConstants.SETTING_DEMMAND_TYPE)) {
            when (preferences.getInt(SettingsConstants.SETTING_DEMMAND_TYPE, SettingsConstants.DEMAND_TYPE_NATIVE)) {
                SettingsConstants.DEMAND_TYPE_NATIVE -> addNativeAds(quotes)
                SettingsConstants.DEMAND_TYPE_STANDARD -> addStandardAds(quotes)
                SettingsConstants.DEMAND_TYPE_VIDEO -> addVideoAds(quotes)
                SettingsConstants.DEMAND_TYPE_AD_TAG -> addAdTags(quotes)
                else -> addNativeAds(quotes)
            }
        } else {
            addNativeAds(quotes)
        }
    }

    open fun addNativeAds(list: ArrayList<ViewType>) {}

    open fun addStandardAds(list: ArrayList<ViewType>) {}

    open fun addVideoAds(list: ArrayList<ViewType>) {}

    open fun addAdTags(list: ArrayList<ViewType>) {}

    override fun onSuccess(list: List<Quote>) {
        val adapterList = ArrayList<ViewType>()
        adapterList.addAll(list)
        injectItems(adapterList)
        view.showItems(adapterList)
    }

    override fun onError(throwable: Throwable) {
        view.showError(throwable)
    }
}