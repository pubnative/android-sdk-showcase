package net.pubnative.sdkshowcase.ui.fragments

import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.pubnative.sdkshowcase.R

import kotlinx.android.synthetic.main.fragment_main.*
import net.pubnative.sdkshowcase.ui.activities.DemandConfigActivity

/**
 * A placeholder fragment containing a simple view.
 */
class MainFragment : Fragment() {

    private val TAG = MainFragment::class.java!!.getSimpleName()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_main, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        demang_config_container.setOnClickListener({ view ->
            DemandConfigActivity.start(context)
        })

        banner_button.setOnClickListener({ view ->

        })

        feed_small_button.setOnClickListener({ view ->

        })

        feed_medium_button.setOnClickListener({ view ->

        })

        interstitial_button.setOnClickListener({ view ->

        })
    }
}
