package net.pubnative.sdkshowcase.ui.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.fragment_banner.*
import kotlinx.android.synthetic.main.item_small_native.view.*
import net.pubnative.sdk.core.request.PNAdModel
import net.pubnative.sdk.core.request.PNRequest
import net.pubnative.sdk.layouts.PNLayout
import net.pubnative.sdk.layouts.PNSmallLayout
import net.pubnative.sdkshowcase.APP_TOKEN
import net.pubnative.sdkshowcase.R
import net.pubnative.sdkshowcase.SMALL_PLACEMENT_ID
import net.pubnative.sdkshowcase.settings.SettingsConstants
import java.lang.Exception

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class BannerFragment : Fragment() {
    val request: PNRequest
    var nativeAdModel: PNAdModel? = null
    var nativeAdView: ViewGroup? = null

    val banner: PNSmallLayout

    init {
        request = PNRequest()
        banner = PNSmallLayout()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_banner, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (preferences.contains(SettingsConstants.SETTING_DEMMAND_TYPE)) {
            when (preferences.getInt(SettingsConstants.SETTING_DEMMAND_TYPE, SettingsConstants.DEMAND_TYPE_NATIVE)) {
                SettingsConstants.DEMAND_TYPE_NATIVE -> {
                    loadNativeBanner()
                }
                SettingsConstants.DEMAND_TYPE_STANDARD -> {
                    loadStandardBanner()
                }
                SettingsConstants.DEMAND_TYPE_VIDEO -> {
                    loadNativeBanner()
                }
                SettingsConstants.DEMAND_TYPE_AD_TAG -> {
                    loadNativeBanner()
                }
                else -> {
                    loadNativeBanner()
                }
            }
        } else {
            loadNativeBanner()
        }
    }

    override fun onResume() {
        super.onResume()
        startTrackingAd()
    }

    override fun onPause() {
        super.onPause()
        stopTrackingAd()
    }

    fun loadStandardBanner() {
        banner.setTrackListener(object : PNLayout.TrackListener {
            override fun onPNLayoutTrackClick(layout: PNLayout?) {
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
        banner.setLoadListener(object : PNLayout.LoadListener {
            override fun onPNLayoutLoadFail(layout: PNLayout?, error: Exception?) {
                Toast.makeText(context,
                        error!!.message ?: "An error occurred while trying to load the ad",
                        Toast.LENGTH_LONG).show()
            }

            override fun onPNLayoutLoadFinish(layout: PNLayout?) {
                banner_container.addView(banner.getView(context))
                banner.startTrackingView()
            }
        })

        banner.load(context, APP_TOKEN, SMALL_PLACEMENT_ID)
    }

    fun loadNativeBanner() {
        request.start(context, APP_TOKEN, SMALL_PLACEMENT_ID, object : PNRequest.Listener {
            override fun onPNRequestLoadFail(request: PNRequest?, error: Exception?) {
                Toast.makeText(context,
                        error?.message ?: "An error occurred while trying to load the ad",
                        Toast.LENGTH_LONG).show()
            }

            override fun onPNRequestLoadFinish(request: PNRequest?, adModel: PNAdModel?) {
                nativeAdModel = adModel
                nativeAdModel?.setListener(object : PNAdModel.Listener {
                    override fun onPNAdClick(adModel: PNAdModel?) {
                        Toast.makeText(context,
                                "onPNAdClick",
                                Toast.LENGTH_LONG).show()
                    }

                    override fun onPNAdImpression(adModel: PNAdModel?) {
                        Toast.makeText(context,
                                "onPNAdImpression",
                                Toast.LENGTH_LONG).show()
                    }
                })
                nativeAdView = LayoutInflater.from(context).inflate(R.layout.item_small_native, banner_container, false) as ViewGroup
                banner_container.addView(nativeAdView)

                nativeAdModel?.withIcon(nativeAdView?.native_icon as ImageView)
                        ?.withTitle(nativeAdView?.native_title as TextView)
                        ?.withDescription(nativeAdView?.native_description as TextView)
                        ?.withCallToAction(nativeAdView?.native_call_to_action as Button)
                        ?.withContentInfoContainer(nativeAdView?.native_disclaimer as FrameLayout)
                        ?.startTracking(nativeAdView)
            }
        })
    }

    fun startTrackingAd() {
        if (nativeAdModel != null && nativeAdView != null) {
            nativeAdModel?.startTracking(nativeAdView)
        }

        banner.startTrackingView()
    }

    fun stopTrackingAd() {
        if (nativeAdModel != null) {
            nativeAdModel?.stopTracking()
        }

        banner.stopTrackingView()
    }
}