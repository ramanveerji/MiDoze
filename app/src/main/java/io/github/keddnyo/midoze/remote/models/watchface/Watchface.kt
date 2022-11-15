package io.github.keddnyo.midoze.remote.models.watchface

import java.net.URL

data class Watchface(
    val title: String,
    val preview: String,
    val watchfaceLink: String
) {
    val watchfaceUrl: URL
        get() = URL(watchfaceLink)
}