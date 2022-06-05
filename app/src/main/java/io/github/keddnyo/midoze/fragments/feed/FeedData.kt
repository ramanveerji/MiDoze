package io.github.keddnyo.midoze.fragments.feed

data class FeedData(
    val deviceName: String,
    val deviceIcon: Int,
    val firmwareReleaseDate: String,
    val firmwareChangelog: String,
    val deviceIndex: Int
)
