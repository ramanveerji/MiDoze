package io.github.keddnyo.midoze.utils

import java.util.*

class Language {
    val defaultLanguage: Locale = Locale.getDefault()

    fun getLanguageList(lang: String): String {
        val arrayOfLanguageCodes = lang.split(",").toTypedArray()
        val arrayOfLanguageNames = arrayListOf<String>()
        val currentLanguage = Locale(defaultLanguage.language.toString())

        for (i in arrayOfLanguageCodes) {
            arrayOfLanguageNames.add(
                Locale(i).getDisplayName(currentLanguage)
            )
        }

        return arrayOfLanguageNames.toString()
            .substring(1, arrayOfLanguageNames.toString().length - 1)
            .replace(", pt-br", "")
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(defaultLanguage) else it.toString() } +
                "."
    }
}