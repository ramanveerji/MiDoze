package io.github.keddnyo.midoze.utils

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.preference.PreferenceManager
import io.github.keddnyo.midoze.objects.RequestHosts
import io.github.keddnyo.midoze.objects.ResponseValues.COUNTRY_CHINA
import io.github.keddnyo.midoze.objects.ResponseValues.COUNTRY_RUSSIA
import io.github.keddnyo.midoze.objects.ResponseValues.COUNTRY_UAE
import io.github.keddnyo.midoze.objects.ResponseValues.COUNTRY_US
import io.github.keddnyo.midoze.objects.ResponseValues.LANGUAGE_CHINA
import io.github.keddnyo.midoze.objects.ResponseValues.LANGUAGE_ENGLISH
import io.github.keddnyo.midoze.objects.ResponseValues.LANGUAGE_RUSSIA
import io.github.keddnyo.midoze.objects.ResponseValues.LANGUAGE_UAE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class FirmwareRequest {

    suspend fun getResponse(
        productionSource: String,
        deviceSource: String,
        appVersion: String,
        appName: String,
        context: Context,
    ): JSONObject = with(context) {

        val prefs: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)

        val country = when (prefs.getString("settings_request_region", "1")) {
            "2" -> COUNTRY_CHINA
            "3" -> COUNTRY_RUSSIA
            "4" -> COUNTRY_UAE
            else -> COUNTRY_US
        }
        val lang = when (prefs.getString("settings_request_region", "1")) {
            "2" -> LANGUAGE_CHINA
            "3" -> LANGUAGE_RUSSIA
            "4" -> LANGUAGE_UAE
            else -> LANGUAGE_ENGLISH
        }

        val requestHost = when (prefs.getString("settings_request_host", "1")) {
            "2" -> getString(RequestHosts.SECOND)
            "3" -> getString(RequestHosts.THIRD)
            else -> getString(RequestHosts.FIRST)
        }

        val uriBuilder: Uri.Builder = Uri.Builder()
        uriBuilder.scheme("https")
            .authority(requestHost)
            .appendPath("devices")
            .appendPath("ALL")
            .appendPath("hasNewVersion")
            .appendQueryParameter("productId", "0")
            .appendQueryParameter("vendorSource", "0")
            .appendQueryParameter("resourceVersion", "0")
            .appendQueryParameter("firmwareFlag", "0")
            .appendQueryParameter("vendorId", "0")
            .appendQueryParameter("resourceFlag", "0")
            .appendQueryParameter("productionSource", productionSource)
            .appendQueryParameter("userid", "0")
            .appendQueryParameter("userId", "0")
            .appendQueryParameter("deviceSource", deviceSource)
            .appendQueryParameter("fontVersion", "0")
            .appendQueryParameter("fontFlag", "0")
            .appendQueryParameter("appVersion", appVersion)
            .appendQueryParameter("appid", "0")
            .appendQueryParameter("callid", "0")
            .appendQueryParameter("channel", "0")
            .appendQueryParameter("country", "0")
            .appendQueryParameter("cv", "0")
            .appendQueryParameter("device", "0")
            .appendQueryParameter("deviceType", "ALL")
            .appendQueryParameter("device_type", "android_phone")
            .appendQueryParameter("firmwareVersion", "0")
            .appendQueryParameter("hardwareVersion", "0")
            .appendQueryParameter("lang", "0")
            .appendQueryParameter("support8Bytes", "true")
            .appendQueryParameter("timezone", "0")
            .appendQueryParameter("v", "0")
            .appendQueryParameter("gpsVersion", "0")
            .appendQueryParameter("baseResourceVersion", "0")

        val request = Request.Builder()
            .url(uriBuilder.toString())
            .addHeader("hm-privacy-diagnostics", "false")
            .addHeader("country", country)
            .addHeader("appplatform", "android_phone")
            .addHeader("hm-privacy-ceip", "0")
            .addHeader("x-request-id", "0")
            .addHeader("timezone", "0")
            .addHeader("channel", "0")
            .addHeader("user-agent", "0")
            .addHeader("cv", "0")
            .addHeader("appname", appName)
            .addHeader("v", "0")
            .addHeader("apptoken", "0")
            .addHeader("lang", lang)
            .addHeader("Host", requestHost)
            .addHeader("Connection", "Keep-Alive")
            .addHeader("accept-encoding", "gzip")
            .addHeader("accept", "*/*")
            .build()

        return withContext(Dispatchers.IO) {
            JSONObject(
                OkHttpClient().newCall(request).execute().body()?.string().toString()
            )
        }
    }

}