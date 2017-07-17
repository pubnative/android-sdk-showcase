package net.pubnative.sdkshowcase.ui.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_banner.*
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.SMALL_AD_TAG_PLACEMENT_ID
import net.pubnative.sdkshowcase.SMALL_NATIVE_PLACEMENT_ID
import net.pubnative.sdkshowcase.SMALL_STANDARD_PLACEMENT_ID
import net.pubnative.sdkshowcase.settings.SettingsConstants
import net.pubnative.sdkshowcase.ui.contracts.BannerFragmentContract
import net.pubnative.sdkshowcase.ui.presenters.AssetGroupBannerPresenter

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class BannerFragment : Fragment(), BannerFragmentContract.View {
    var presenter: BannerFragmentContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = getDemandTypePresenter()
    }

    fun getDemandTypePresenter(): BannerFragmentContract.Presenter {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        when (preferences.getInt(SettingsConstants.SETTING_DEMMAND_TYPE, SettingsConstants.DEMAND_TYPE_NATIVE)) {
            SettingsConstants.DEMAND_TYPE_NATIVE -> return AssetGroupBannerPresenter(context, this, SMALL_NATIVE_PLACEMENT_ID)
            SettingsConstants.DEMAND_TYPE_STANDARD -> return AssetGroupBannerPresenter(context, this, SMALL_STANDARD_PLACEMENT_ID)
            SettingsConstants.DEMAND_TYPE_VIDEO -> return AssetGroupBannerPresenter(context, this, SMALL_NATIVE_PLACEMENT_ID)
            SettingsConstants.DEMAND_TYPE_AD_TAG -> return AssetGroupBannerPresenter(context, this, SMALL_AD_TAG_PLACEMENT_ID)
            else -> return AssetGroupBannerPresenter(context, this, SMALL_NATIVE_PLACEMENT_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_banner, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.stopTracking()
    }

    override fun setView(adView: View) {
        if (isResumed) {
            banner_container.addView(adView)
            presenter?.startTracking()
        }
    }
}