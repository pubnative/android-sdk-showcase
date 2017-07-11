package net.pubnative.sdkshowcase.ui.contracts

import android.view.View
import android.view.ViewGroup
import net.pubnative.sdkshowcase.ui.presenters.BannerPresenter

/**
 * Created by erosgarciaponte on 11.07.17.
 */
interface BannerFragmentContract {
    interface View {
        fun setView(adView: android.view.View)
    }

    interface Presenter: BannerPresenter {

    }
}