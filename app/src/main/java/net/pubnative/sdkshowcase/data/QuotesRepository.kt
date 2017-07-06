package net.pubnative.sdkshowcase.data

import android.content.Context
import net.pubnative.sdkshowcase.data.models.Quote
import net.pubnative.sdkshowcase.data.source.DataSource
import net.pubnative.sdkshowcase.data.source.local.QuotesLocalDataSource

/**
 * Created by erosgarciaponte on 06.07.17.
 */
class QuotesRepository(val context: Context) : DataSource<Quote> {
    private val mLocalDataSource : QuotesLocalDataSource

    init {
        mLocalDataSource = QuotesLocalDataSource(context)
    }

    override fun getAll(callback: DataSource.Callback<Quote>) {
        mLocalDataSource.getAll(callback)
    }
}