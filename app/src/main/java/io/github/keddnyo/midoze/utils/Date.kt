package io.github.keddnyo.midoze.utils

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat

class Date {

    @SuppressLint("SimpleDateFormat")
    fun format(date: String): String = with(Language().defaultLanguage) {

        val inputFormat = SimpleDateFormat("yyyy-MM-dd")

        val outputFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, this)

        val inputDate = inputFormat.parse(date)

        return if (inputDate != null) {
            outputFormat.format(inputDate)
        } else {
            ""
        }

    }

}