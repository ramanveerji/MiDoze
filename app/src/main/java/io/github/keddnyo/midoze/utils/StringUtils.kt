package io.github.keddnyo.midoze.utils

import android.annotation.SuppressLint
import io.github.keddnyo.midoze.R
import java.text.DateFormat
import java.text.SimpleDateFormat

class StringUtils {
    val tabTitles = arrayOf(
        R.string.feed_title,
        R.string.settings_title
    )

    fun cleanChangelog(changeLog: String): String {
        var c = changeLog.substringBefore('#')
        c = c.replace(";", "")
        return c
    }

    fun cleanResponse(string: String): String {
        return string.replace("\\/", "/")
    }

    @SuppressLint("SimpleDateFormat")
    fun getLocalFirmwareDate(firmwareDateString: String): String = with(Language().getCurrent()) {

        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, this)

        val inputDate = inputFormat.parse(firmwareDateString)

        val outputDate = if (inputDate != null) {
            outputFormat.format(inputDate)
        } else {
            ""
        }

        return outputDate
    }
}