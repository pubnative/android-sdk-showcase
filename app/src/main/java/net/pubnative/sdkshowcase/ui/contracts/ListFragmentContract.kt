package net.pubnative.sdkshowcase.ui.contracts

import net.pubnative.sdkshowcase.ui.views.ViewType

/**
 * Created by erosgarciaponte on 12.07.17.
 */
interface ListFragmentContract {
    interface View {
        fun showItems(items : ArrayList<ViewType>)

        fun showError(throwable: Throwable)
    }

    interface Presenter {
        fun loadList()

        fun injectItems(list: ArrayList<ViewType>)
    }
}