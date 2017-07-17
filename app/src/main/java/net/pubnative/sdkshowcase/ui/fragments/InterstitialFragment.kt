package net.pubnative.sdkshowcase.ui.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_interstitial.*
import net.pubnative.sdk.layouts.PNLargeLayout
import net.pubnative.sdk.layouts.PNLayout
import net.pubnative.sdkshowcase.*
import net.pubnative.sdkshowcase.settings.SettingsConstants
import java.lang.Exception

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class InterstitialFragment : Fragment() {

    private val TAG = InterstitialFragment::class.java.simpleName

    val largeLayout: PNLargeLayout

    init {
        largeLayout = PNLargeLayout()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_interstitial, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupInterstitial()
    }

    fun setupInterstitial() {
        largeLayout.setLoadListener(object : PNLayout.LoadListener {
            override fun onPNLayoutLoadFail(layout: PNLayout?, error: Exception?) {
                Log.d(TAG, error!!.message ?: "An error occurred while trying to load the ad")
                Toast.makeText(context,
                        error!!.message ?: "An error occurred while trying to load the ad",
                        Toast.LENGTH_LONG).show()
                progress.visibility = View.GONE
            }

            override fun onPNLayoutLoadFinish(layout: PNLayout?) {
                if (largeLayout.isReady) {
                    showInterstitial()
                }
            }
        })

        largeLayout.setTrackListener(object : PNLayout.TrackListener {
            override fun onPNLayoutTrackClick(layout: PNLayout?) {
                largeLayout.hide()
                Log.d(TAG, "onPNLayoutTrackClick")
            }

            override fun onPNLayoutTrackImpression(layout: PNLayout?) {
                Log.d(TAG, "onPNLayoutTrackImpression")
            }
        })

        largeLayout.setViewListener(object : PNLargeLayout.ViewListener {
            override fun onPNLayoutViewHidden(layout: PNLayout?) {
                Log.d(TAG, "onPNLayoutViewHidden")
            }

            override fun onPNLayoutViewShown(layout: PNLayout?) {
                Log.d(TAG, "onPNLayoutViewShown")
            }
        })

        largeLayout.load(context, APP_TOKEN, getDemandTypePlacement())
    }

    fun getDemandTypePlacement(): String {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences.contains(SettingsConstants.SETTING_DEMMAND_TYPE)) {
            when (preferences.getInt(SettingsConstants.SETTING_DEMMAND_TYPE, SettingsConstants.DEMAND_TYPE_NATIVE)) {
                SettingsConstants.DEMAND_TYPE_NATIVE -> return LARGE_NATIVE_PLACEMENT_ID
                SettingsConstants.DEMAND_TYPE_STANDARD -> return LARGE_STANDARD_PLACEMENT_ID
                SettingsConstants.DEMAND_TYPE_VIDEO -> return LARGE_VIDEO_PLACEMENT_ID
                SettingsConstants.DEMAND_TYPE_AD_TAG -> return LARGE_AD_TAG_PLACEMENT_ID
                else -> return LARGE_NATIVE_PLACEMENT_ID
            }
        } else {
            return LARGE_NATIVE_PLACEMENT_ID
        }
    }

    fun showInterstitial() {
        if (isResumed) {
            progress.visibility = View.INVISIBLE
            largeLayout.show()
        }
    }
}