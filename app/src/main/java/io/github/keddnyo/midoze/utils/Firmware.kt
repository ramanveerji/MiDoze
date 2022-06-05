package io.github.keddnyo.midoze.utils

class Firmware {

    fun fixChangelog(inputString: String): String {
        var changelog = inputString.substringBefore('#')
        changelog = changelog.replace(";", "")
        return changelog
    }

    fun fixResponse(string: String): String {
        return string.replace("\\/", "/")
    }
}