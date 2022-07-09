package io.github.keddnyo.midoze.utils.devices

import org.json.JSONObject

data class DeviceData(
    val icon: Int,
    val name: String,
    val firmware: JSONObject,
    val buildTime: String,
    val deviceSource: String,
    val productionSource: String
)