package io.github.keddnyo.midoze.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import androidx.preference.PreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import io.github.keddnyo.midoze.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class DozeRequestJava {
    /*Boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
        for (info., info.)
    }*/

    public JSONObject getFirmwareLinks(
            String productionSource,
            String deviceSource,
            String appVersion,
            String appName,
            Context context
    ) throws IOException {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        String country;
        String lang;
        switch (prefs.getString("settings_request_region", "1")) {
            case "2":
                country = "CH";
                lang = "zh_CH";
                break;
            case "3":
                country = "RU";
                lang = "ru_RU";
                break;
            case "4":
                country = "AR";
                lang = "ar_AR";
                break;
            default:
                country = "US";
                lang = "en_US";
                break;
        }

        String requestHost;
        switch (prefs.getString("settings_request_host", "1")) {
            case "2":
                requestHost = context.getString(R.string.request_host_second);
                break;
            case "3":
                requestHost = context.getString(R.string.request_host_third);
                break;
            default:
                requestHost = context.getString(R.string.request_host_first);
                break;
        }

        Uri.Builder uriBuilder = new Uri.Builder();
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
                .appendQueryParameter("baseResourceVersion", "0");

        Request request = new Request.Builder()
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
                .build();

        JSONObject response = null;
        try {
            response = new JSONObject(Objects.requireNonNull(new OkHttpClient().newCall(request).execute().body()).string());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return response;
    }
}
