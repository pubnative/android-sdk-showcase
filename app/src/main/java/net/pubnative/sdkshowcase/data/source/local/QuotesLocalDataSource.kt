package net.pubnative.sdkshowcase.data.source.local

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import net.pubnative.sdkshowcase.data.models.Quote
import net.pubnative.sdkshowcase.data.source.DataSource
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.util.readTextAndClose

/**
 * Created by erosgarciaponte on 06.07.17.
 */
class QuotesLocalDataSource(val context: Context) : DataSource<Quote> {

    override fun getAll(callback: DataSource.Callback<Quote>) {
        val quotesJson = context.resources.openRawResource(R.raw.quotes).readTextAndClose()

        val moshi: Moshi = Moshi.Builder().build()
        val listData = Types.newParameterizedType(List::class.java, Quote::class.java)
        val adapter: JsonAdapter<List<Quote>> = moshi.adapter(listData)
        val list = adapter.fromJson(quotesJson)

        callback?.onSuccess(list!!)
    }
}