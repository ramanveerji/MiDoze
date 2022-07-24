package io.github.keddnyo.midoze.utils

import java.text.DateFormat
import java.text.SimpleDateFormat

class StringUtils {
    fun getChangelogFixed(changeLog: String): String {
        var c = changeLog.substringBefore('#')
        c = c.replace(";", "")
        return c
    }

    fun getExtrasFixed(string: String): String {
        return string.replace("\\/", "/")
    }
}