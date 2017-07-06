package net.pubnative.sdkshowcase.data.source

/**
 * Created by erosgarciaponte on 06.07.17.
 */
interface DataSource<T> {
    fun getAll(callback: Callback<T>)

    interface Callback<T> {
        fun onSuccess(list: List<T>)
        fun onError(throwable: Throwable)
    }
}