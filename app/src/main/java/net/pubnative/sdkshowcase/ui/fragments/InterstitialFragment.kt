package net.pubnative.sdkshowcase.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_interstitial.*
import net.pubnative.sdk.layouts.PNLargeLayout
import net.pubnative.sdk.layouts.PNLayout
import net.pubnative.sdkshowcase.APP_TOKEN
import net.pubnative.sdkshowcase.LARGE_PLACEMENT_ID
import net.pubnative.sdkshowcase.R
import java.lang.Exception

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class InterstitialFragment : Fragment() {

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

        largeLayout.setLoadListener(object : PNLayout.LoadListener {
            override fun onPNLayoutLoadFail(layout: PNLayout?, error: Exception?) {
                Toast.makeText(context,
                        error!!.message ?: "An error occurred while trying to load the ad",
                        Toast.LENGTH_LONG).show()
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
                Toast.makeText(context,
                        "onPNLayoutTrackClick",
                        Toast.LENGTH_LONG).show()
            }

            override fun onPNLayoutTrackImpression(layout: PNLayout?) {
                Toast.makeText(context,
                        "onPNLayoutTrackImpression",
                        Toast.LENGTH_LONG).show()
            }
        })

        largeLayout.setViewListener(object : PNLargeLayout.ViewListener {
            override fun onPNLayoutViewHidden(layout: PNLayout?) {
                Toast.makeText(context,
                        "onPNLayoutViewHidden",
                        Toast.LENGTH_LONG).show()
            }

            override fun onPNLayoutViewShown(layout: PNLayout?) {
                Toast.makeText(context,
                        "onPNLayoutViewShown",
                        Toast.LENGTH_LONG).show()
            }
        })

        largeLayout.load(context, APP_TOKEN, LARGE_PLACEMENT_ID)
    }

    fun showInterstitial() {
        progress.visibility = View.INVISIBLE
        largeLayout.show()
    }
}