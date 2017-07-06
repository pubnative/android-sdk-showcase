package net.pubnative.sdkshowcase.util

import java.io.InputStream
import java.nio.charset.Charset

/**
 * Created by erosgarciaponte on 06.07.17.
 */
fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8) : String {
    return this.bufferedReader(charset).use { it.readText() }
}