package io.github.keddnyo.midoze.utils

import android.Manifest
import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.webkit.URLUtil
import androidx.core.app.ActivityCompat
import io.github.keddnyo.midoze.R

class Download(context: Context) {

    private val downloadContext = context

    private val permissionCheck = ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    private val permissionGranted = PackageManager.PERMISSION_GRANTED

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            downloadContext as Activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1
        )
    }

    fun getFirmwareFile(
        fileUrl: String,
        subName: String,
    ) = with(downloadContext) {
        if (permissionCheck != permissionGranted) {
            requestPermission()
        } else {
            val downloadManager =
                getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val fileName = URLUtil.guessFileName(fileUrl, "?", "?")
            val request = DownloadManager.Request(Uri.parse(fileUrl))

            request.setTitle(fileName)
            request.setNotificationVisibility(1)
            request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "${getString(R.string.app_name)}/$subName/$fileName"
            )
            /*request.setDestinationInExternalFilesDir(
                downloadContext, filesDir.toString(), ""
            )*/

            downloadManager.enqueue(request)
        }
    }
}