package io.github.keddnyo.midoze.objects

import org.json.JSONObject
import java.net.URL

object RemoteJson {

    val FIRMWARE_LATEST = JSONObject(URL("https://schakal.ru/fw/latest.json").readText())

    val QUERY_PARAMS = JSONObject(URL("https://schakal.ru/fw/dev_apps.json").readText())

    val APP_RELEASE_LATEST = JSONObject(URL("https://api.github.com/repos/keddnyo/MiDoze/releases/latest").readText())

}