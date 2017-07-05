package net.pubnative.sdkshowcase.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_feed_small.*
import net.pubnative.sdkshowcase.R

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class FeedSmallActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_small)
        setSupportActionBar(toolbar)
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, FeedSmallActivity::class.java)
            context.startActivity(intent)
        }
    }
}