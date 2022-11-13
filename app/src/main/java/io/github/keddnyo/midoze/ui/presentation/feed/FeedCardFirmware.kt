package io.github.keddnyo.midoze.ui.presentation.feed

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import io.github.keddnyo.midoze.remote.models.firmware.Firmware
import io.github.keddnyo.midoze.remote.models.firmware.FirmwareDownload
import io.github.keddnyo.midoze.remote.models.firmware.FirmwareType
import io.github.keddnyo.midoze.remote.services.download.DownloadStatus
import io.github.keddnyo.midoze.remote.services.download.download

@Composable
fun FeedCardFirmware(
    firmware: Firmware?
) {
    firmware?.run {
        val context = LocalContext.current
        val firmwareFileLinkArray = arrayListOf<FirmwareDownload>()

        firmwareUrl?.let { firmwareUrl ->
            firmwareFileLinkArray.add(
                FirmwareDownload(
                    fileType = FirmwareType.Firmware,
                    fileVersion = firmwareVersion.toString(),
                    address = firmwareUrl
                )
            )
        }
        resourceUrl?.let { resourceUrl ->
            firmwareFileLinkArray.add(
                FirmwareDownload(
                    fileType = FirmwareType.Resource,
                    fileVersion = resourceVersion.toString(),
                    address = resourceUrl
                )
            )
        }
        baseResourceUrl?.let { baseResourceUrl ->
            firmwareFileLinkArray.add(
                FirmwareDownload(
                    fileType = FirmwareType.BaseResource,
                    fileVersion = baseResourceVersion.toString(),
                    address = baseResourceUrl
                )
            )
        }
        fontUrl?.let { fontUrl ->
            firmwareFileLinkArray.add(
                FirmwareDownload(
                    fileType = FirmwareType.Font,
                    fileVersion = fontVersion.toString(),
                    address = fontUrl
                )
            )
        }
        gpsUrl?.let { gpsUrl ->
            firmwareFileLinkArray.add(
                FirmwareDownload(
                    fileType = FirmwareType.Gps,
                    fileVersion = gpsVersion.toString(),
                    address = gpsUrl
                )
            )
        }

        FeedCard(
            icon = device.devicePreview,
            title = device.deviceName,
            subtitle1 = firmwareVersion?.let { "FW: $it" },
            subtitle2 = resourceVersion?.let { "RES: $it" },
            summary = changeLog,
            onClick = {
                firmwareFileLinkArray.forEach { firmwareFile ->
                    val download = download(
                        context = context,
                        deviceName = device.deviceName,
                        firmwareDownload = firmwareFile
                    )

                    when (download) {
                        is DownloadStatus.Successfully -> {
                            Toast.makeText(context, "Starting download", Toast.LENGTH_SHORT).show()
                        }
                        is DownloadStatus.Failed -> {
                            Toast.makeText(context, "Download failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        )
    }
}