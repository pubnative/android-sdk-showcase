package net.pubnative.sdkshowcase.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import net.pubnative.sdkshowcase.R

/**
 * Created by erosgarciaponte on 05.07.17.
 */
class DemandConfigActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demand_config)
        setSupportActionBar(toolbar)
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, DemandConfigActivity::class.java)
            context.startActivity(intent)
        }
    }
}