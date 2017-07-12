package net.pubnative.sdkshowcase.ui.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.pubnative.sdkshowcase.*
import net.pubnative.sdkshowcase.data.models.*
import net.pubnative.sdkshowcase.settings.SettingsConstants
import net.pubnative.sdkshowcase.ui.contracts.ListFragmentContract
import net.pubnative.sdkshowcase.ui.presenters.FeedMediumPresenter
import net.pubnative.sdkshowcase.ui.views.ViewType

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class FeedMediumFragment : RecyclerViewFragment() {

    override fun getViewPresenter(): ListFragmentContract.Presenter {
        return FeedMediumPresenter(context, this)
    }
}