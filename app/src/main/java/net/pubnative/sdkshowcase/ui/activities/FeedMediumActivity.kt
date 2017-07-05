package net.pubnative.sdkshowcase.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_feed_medium.*
import net.pubnative.sdkshowcase.R

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class FeedMediumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_medium)
        setSupportActionBar(toolbar)
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, FeedMediumActivity::class.java)
            context.startActivity(intent)
        }
    }
}