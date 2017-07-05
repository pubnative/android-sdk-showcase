package net.pubnative.sdkshowcase.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by erosgarciaponte on 05.07.17.
 */
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}