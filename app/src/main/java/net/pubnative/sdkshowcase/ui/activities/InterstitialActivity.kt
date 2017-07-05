package net.pubnative.sdkshowcase.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_interstitial.*
import net.pubnative.sdkshowcase.R

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class InterstitialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interstitial)
        setSupportActionBar(toolbar)
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, InterstitialActivity::class.java)
            context.startActivity(intent)
        }
    }
}