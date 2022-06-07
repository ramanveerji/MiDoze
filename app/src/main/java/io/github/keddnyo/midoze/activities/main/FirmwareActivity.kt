package io.github.keddnyo.midoze.activities.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.webkit.URLUtil
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import com.google.android.material.card.MaterialCardView
import io.github.keddnyo.midoze.R
import io.github.keddnyo.midoze.utils.*
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.File


class FirmwareActivity : AppCompatActivity() {

    private val context = this@FirmwareActivity

    private var firmwareResponse = JSONObject()
    private var deviceNameValue: String = ""

    private val responseFirmwareTagsArray = arrayOf(
        "firmwareUrl",
        "resourceUrl",
        "baseResourceUrl",
        "fontUrl",
        "gpsUrl"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firmware)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        runBlocking {
            withContext(Dispatchers.IO) {
                init()
            }
        }
    }

    private suspend fun init() = withContext(Dispatchers.IO) {
        val fileLinks = arrayListOf<String>()

        val deviceNameTextView: TextView = findViewById(R.id.deviceNameTextView)
        val deviceIconTextView: ImageView = findViewById(R.id.deviceIconImageView)
        val firmwareVersionTextView: TextView = findViewById(R.id.firmwareVersionTextView)
        val firmwareChangelogTextView: TextView = findViewById(R.id.firmwareChangelogTextView)
        val firmwareChangelogLayout: MaterialCardView = findViewById(R.id.firmware_changelog_layout)
        val firmwareLanguagesTextView: TextView = findViewById(R.id.firmwareLanguagesTextView)
        val firmwareInstallButton: Button = findViewById(R.id.firmwareInstallButton)
        val firmwareDownload: ImageView = findViewById(R.id.firmwareDownload)
        val firmwareShare: ImageView = findViewById(R.id.firmwareShare)

        deviceNameValue = intent.getStringExtra("deviceName").toString()
        val deviceSourceValue = intent.getIntExtra("deviceSource", 0).toString()
        val productionSourceValue = intent.getStringExtra("productionSource").toString()
        val appNameValue = intent.getStringExtra("appname").toString()
        val appVersionValue = intent.getStringExtra("appVersion").toString()

        firmwareResponse = FirmwareRequest().getResponse(
            productionSourceValue,
            deviceSourceValue,
            appVersionValue,
            appNameValue,
            context
        )

        deviceNameTextView.text = deviceNameValue

        when {
            deviceNameValue.contains("Mi Band", true) -> {
                deviceIconTextView.setImageResource(R.drawable.ic_xiaomi)
            }
            deviceNameValue.contains("Zepp", true) -> {
                deviceIconTextView.setImageResource(R.drawable.ic_zepp)
            }
            else -> {
                deviceIconTextView.setImageResource(R.drawable.ic_amazfit)
            }
        }

        if (firmwareResponse.has("firmwareVersion")) {
            firmwareVersionTextView.text = firmwareResponse.getString(
                "firmwareVersion"
            )
        } else {
            runOnUiThread {
                Display().showToast(context, getString(R.string.firmware_not_found))
                Display().showToast(context, getString(R.string.firmware_try_switch_region))
            }
            finish()
        }
        if (firmwareResponse.has("changeLog")) {
            firmwareChangelogTextView.text = Firmware().fixChangelog(
                firmwareResponse.getString("changeLog")
            )
        } else {
            firmwareChangelogLayout.visibility = View.GONE
        }
        if (firmwareResponse.has("lang")) {
            firmwareLanguagesTextView.text = Language().getLanguageList(
                firmwareResponse.getString("lang")
            )
        }

        val packageManager = context.packageManager

        runOnUiThread {
            firmwareInstallButton.isEnabled = PackageManager().isPackageInstalled("com.mc.miband1", packageManager) || PackageManager().isPackageInstalled("com.mc.amazfit1", packageManager)
        }

        firmwareInstallButton.setOnClickListener {
            /*val packageManager = context.packageManager
            if (deviceNameValue.contains("Mi Band",
                    true) && PackageManager().isPackageInstalled("com.mc.miband1", packageManager)
            ) {

            } else if ((deviceNameValue.contains("Amazfit",
                    true) || deviceNameValue.contains("Amazfit",
                    true)) && PackageManager().isPackageInstalled("com.mc.amazfit1", packageManager)
            ) {

            }*/

            for (i in responseFirmwareTagsArray) {
                if (firmwareResponse.has(i)) {
                    fileLinks.add(firmwareResponse.getString(i))
                }
            }

            /*if (getTempFiles(fileLinks)) {
                createZip()
                sendZip()
            }*/

            sendZip()
        }

        firmwareDownload.setOnClickListener {
            getFirmware(firmwareResponse, context, deviceNameValue)
        }

        firmwareShare.setOnLongClickListener {
            shareFirmware()
            true
        }
    }

    private val filesNames = arrayListOf<String>()
    private val filePath =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

    private fun getTempFiles(fileLinks: ArrayList<String>): Boolean {
        /*ActivityCompat.requestPermissions(this, arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.MANAGE_EXTERNAL_STORAGE
        ), 1
        )*/

        //val filePath = context.filesDir

        for (i in fileLinks) {
            val fileName = URLUtil.guessFileName(i, "?", "?")

            filesNames.add("$filePath/MiDoze/_tmp/$fileName")

            /*GlobalScope.async {

                /*File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)}/MiDoze/_tmp", fileName).let { sourceFile ->
                    sourceFile.copyTo(File(filePath, fileName))
                    sourceFile.delete()
                }*/
            }*/
            Download(context).getFirmwareFile(i, "_tmp")
            if (i == fileLinks.last()) {
                return true
            }
        }
        return false
    }

    private fun createZip() {
        //FileManager(filePath).createZip(filesNames)

        /*File(filePath, "archive.zip").let { sourceFile ->
            sourceFile.copyTo(File(filePath, "archive.bin"))
            sourceFile.delete()
        }

        File(filePath, "archive.bin").copyTo(File("${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)}", "archive.bin"))
    */
    }

    fun sendZip() {
        val outputZipFile = File(File("/storage/emulated/0/Download/MiDoze/_tmp").path, "archive.bin")
        installZipFirmware(outputZipFile)
    }

    private fun getFirmware(
        jsonObject: JSONObject,
        context: Context,
        deviceName: String,
    ) {
        for (i in responseFirmwareTagsArray) {
            if (jsonObject.has(i)) {
                val urlString = jsonObject.getString(i)
                Download(context).getFirmwareFile(urlString, deviceName)
            }
        }
        Display().showToast(context, getString(R.string.downloading_toast))
    }

    private fun shareFirmware() {
        val firmwareLinks = arrayListOf<String>()

        for (i in responseFirmwareTagsArray) {
            if (firmwareResponse.has(i)) {
                firmwareLinks.add(firmwareResponse.getString(i))
            }
        }

        val shareContent = "$deviceNameValue\n${firmwareLinks.joinToString("\n")}"

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareContent)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, deviceNameValue)
        startActivity(shareIntent)
    }

    private fun installZipFirmware(filePath: File) {
        /*val shareIntent = Intent(Intent.ACTION_VIEW).apply {
            val data = FileProvider.getUriForFile(context,
                BuildConfig.APPLICATION_ID + ".provider",
                filePath)
            intent.setDataAndType(data, "application/octet-stream")
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            /*putExtra(
                Intent.EXTRA_SUBJECT,
                "Sharing file from the AppName"
            )
            putExtra(
                Intent.EXTRA_TEXT,
                "Sharing file from the AppName with some description"
            )*/
            val fileURI = FileProvider.getUriForFile(
                context, context.packageName + ".provider",
                (filePath)
            )
            putExtra(Intent.ACTION_VIEW, fileURI)
        }
        startActivity(Intent.createChooser(shareIntent,"Open File..."))*/



        /*val intent = Intent(Intent.ACTION_VIEW)
        val data = FileProvider.getUriForFile(context,
            BuildConfig.APPLICATION_ID + ".provider",
            filePath)
        intent.setDataAndType(data, "application/octet-stream")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(intent,"Open File..."))*/

        val uri: Uri = FileProvider.getUriForFile(this, "io.github.keddnyo.midoze.provider", filePath)

        val intent = ShareCompat.IntentBuilder.from(this)
            .setStream(uri) // uri from FileProvider
            .setType("application/octet-stream")
            .intent
            .setAction(Intent.ACTION_VIEW) //Change if needed
            .setDataAndType(uri, "application/octet-stream")
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        startActivity(intent)



        /*val intentShare = Intent(Intent.ACTION_VIEW)
        intentShare.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        val mimeType = "application/octet-stream"  // The file here is the nativescript file object (fs.File)
        val context = this@FirmwareActivity
        val uri = FileProvider.getUriForFile(context,
            BuildConfig.APPLICATION_ID + ".provider",
            filePath) // Here add ".provider" after your app package name
        intentShare.setDataAndType(uri, mimeType);
        intentShare.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(intentShare, "Open File..."))*/
    }
}