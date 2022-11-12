package io.github.keddnyo.midoze.local.repositories

import io.github.keddnyo.midoze.R
import io.github.keddnyo.midoze.local.models.firmware.Application
import io.github.keddnyo.midoze.local.models.firmware.Device

val deviceList = arrayOf(

    // Mi Band
    Device(
        deviceName = "Xiaomi Mi Band 3",
        devicePreview = R.drawable.mi_band_3i,
        deviceSource = 31,
        productionSource = 256,
        application = Application.ZeppLife,
        region = Region.Default,
    ),
    Device(
        deviceName = "Xiaomi Mi Band 4 GL",
        devicePreview = R.drawable.mi_band_4,
        deviceSource = 25,
        productionSource = 257,
        application = Application.ZeppLife,
        region = Region.Default,
    ),
    Device(
        deviceName = "Xiaomi Mi Band 4 NFC",
        devicePreview = R.drawable.mi_band_4,
        deviceSource = 24,
        productionSource = 256,
        application = Application.ZeppLife,
        region = Region.Default,
    ),
    Device(
        deviceName = "Xiaomi Mi Band 5",
        devicePreview = R.drawable.mi_band_5_nfc,
        deviceSource = 59,
        productionSource = 257,
        application = Application.ZeppLife,
        region = Region.Default,
    ),
    Device(
        deviceName = "Xiaomi Mi Band 5 NFC",
        devicePreview = R.drawable.mi_band_5_nfc,
        deviceSource = 58,
        productionSource = 256,
        application = Application.ZeppLife,
        region = Region.Default,
    ),
    Device(
        deviceName = "Xiaomi Mi Band Smart 6 GL",
        devicePreview = R.drawable.mi_band_6,
        deviceSource = 212,
        productionSource = 257,
        application = Application.ZeppLife,
        region = Region.Default,
    ),
    Device(
        deviceName = "Xiaomi Mi Band Smart 6 NFC",
        devicePreview = R.drawable.mi_band_6,
        deviceSource = 211,
        productionSource = 256,
        application = Application.ZeppLife,
        region = Region.Default,
    ),
    Device(
        deviceName = "Xiaomi Mi Smart Band 7",
        devicePreview = R.drawable.mi_band_7,
        deviceSource = 260,
        productionSource = 256,
        application = Application.ZeppLife,
        region = Region.Default,
    ),
    Device(
        deviceName = "Xiaomi Mi Smart Band 7",
        devicePreview = R.drawable.mi_band_7,
        deviceSource = 262,
        productionSource = 258,
        application = Application.ZeppLife,
        region = Region.Default,
    ),
    Device(
        deviceName = "Xiaomi Mi Smart Band 7",
        devicePreview = R.drawable.mi_band_7,
        deviceSource = 263,
        productionSource = 259,
        application = Application.ZeppLife,
        region = Region.Default,
    ),
    Device(
        deviceName = "Xiaomi Mi Smart Band 7",
        devicePreview = R.drawable.mi_band_7,
        deviceSource = 264,
        productionSource = 260,
        application = Application.ZeppLife,
        region = Region.Default,
    ),
    Device(
        deviceName = "Xiaomi Mi Smart Band 7",
        devicePreview = R.drawable.mi_band_7,
        deviceSource = 265,
        productionSource = 261,
        application = Application.ZeppLife,
        region = Region.Default,
    ),

    // Amazfit Band
    Device(
        deviceName = "Amazfit Band 5",
        devicePreview = R.drawable.amazfit_band_5,
        deviceSource = 73,
        productionSource = 256,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit Band 7",
        devicePreview = R.drawable.amazfit_band_7,
        deviceSource = 254,
        productionSource = 259,
        application = Application.Zepp,
        region = Region.Default,
    ),

    // Amazfit Ares
    Device(
        deviceName = "Amazfit Ares",
        devicePreview = R.drawable.amazfit_ares,
        deviceSource = 65,
        productionSource = 256,
        application = Application.Zepp,
        region = Region.Chinese,
    ),

    // Amazfit Bip
    Device(
        deviceName = "Amazfit Bip CH",
        devicePreview = R.drawable.amazfit_bip,
        deviceSource = 12,
        productionSource = 256,
        application = Application.ZeppLife,
        region = Region.Chinese,
    ),
    Device(
        deviceName = "Amazfit Bip Lite CH",
        devicePreview = R.drawable.amazfit_bip,
        deviceSource = 39,
        productionSource = 256,
        application = Application.ZeppLife,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit Bip Lite GL",
        devicePreview = R.drawable.amazfit_bip,
        deviceSource = 42,
        productionSource = 257,
        application = Application.ZeppLife,
        region = Region.Chinese,
    ),
    Device(
        deviceName = "Amazfit Bip S CH",
        devicePreview = R.drawable.amazfit_bip_s,
        deviceSource = 20,
        productionSource = 256,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit Bip S GL",
        devicePreview = R.drawable.amazfit_bip_s,
        deviceSource = 28,
        productionSource = 258,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit Bip S Lite GL",
        devicePreview = R.drawable.amazfit_bip_s,
        deviceSource = 29,
        productionSource = 259,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit Bip 3",
        devicePreview = R.drawable.amazfit_bip_3,
        deviceSource = 257,
        productionSource = 257,
        application = Application.Zepp,
        region = Region.Chinese,
    ),
    Device(
        deviceName = "Amazfit Bip 3 Pro",
        devicePreview = R.drawable.amazfit_bip_3,
        deviceSource = 256,
        productionSource = 256,
        application = Application.Zepp,
        region = Region.Chinese,
    ),

    // Amazfit Pop
    Device(
        deviceName = "Amazfit Pop",
        devicePreview = R.drawable.amazfit_bip_u,
        deviceSource = 68,
        productionSource = 258,
        application = Application.Zepp,
        region = Region.Chinese,
    ),
    Device(
        deviceName = "Amazfit Pop Pro",
        devicePreview = R.drawable.amazfit_bip_u,
        deviceSource = 67,
        productionSource = 256,
        application = Application.Zepp,
        region = Region.Chinese,
    ),
    Device(
        deviceName = "Amazfit Bip U",
        devicePreview = R.drawable.amazfit_bip_u,
        deviceSource = 70,
        productionSource = 259,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit Bip U Pro",
        devicePreview = R.drawable.amazfit_bip_u,
        deviceSource = 69,
        productionSource = 257,
        application = Application.Zepp,
        region = Region.Default,
    ),

    // Amazfit GTR
    Device(
        deviceName = "Amazfit GTR 42 CH",
        devicePreview = R.drawable.amazfit_gtr,
        deviceSource = 37,
        productionSource = 256,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit GTR 42 GL",
        devicePreview = R.drawable.amazfit_gtr,
        deviceSource = 38,
        productionSource = 257,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit GTR 42 SWK",
        devicePreview = R.drawable.amazfit_gtr,
        deviceSource = 51,
        productionSource = 260,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit GTR 42 SWK GL",
        devicePreview = R.drawable.amazfit_gtr,
        deviceSource = 52,
        productionSource = 261,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit GTR 47 Disney",
        devicePreview = R.drawable.amazfit_gtr,
        deviceSource = 54,
        productionSource = 259,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit GTR 47 CH",
        devicePreview = R.drawable.amazfit_gtr,
        deviceSource = 35,
        productionSource = 256,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit GTR 47 GL",
        devicePreview = R.drawable.amazfit_gtr,
        deviceSource = 36,
        productionSource = 256,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit GTR 47 Lite GL",
        devicePreview = R.drawable.amazfit_gtr,
        deviceSource = 46,
        productionSource = 258,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit GTR 2",
        devicePreview = R.drawable.amazfit_gtr_2,
        deviceSource = 244,
        productionSource = 258,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit GTR 2 CH",
        devicePreview = R.drawable.amazfit_gtr_2,
        deviceSource = 63,
        productionSource = 256,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit GTR 2 GL",
        devicePreview = R.drawable.amazfit_gtr_2,
        deviceSource = 64,
        productionSource = 257,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit GTR 2e CH",
        devicePreview = R.drawable.amazfit_gtr_2e,
        deviceSource = 206,
        productionSource = 256,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit GTR 2e GL",
        devicePreview = R.drawable.amazfit_gtr_2e,
        deviceSource = 209,
        productionSource = 257,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit GTR 2 eSIM",
        devicePreview = R.drawable.amazfit_gtr_2,
        deviceSource = 98,
        productionSource = 256,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit GTR 3 CH",
        devicePreview = R.drawable.amazfit_gtr_3,
        deviceSource = 226,
        productionSource = 256,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit GTR 3 GL",
        devicePreview = R.drawable.amazfit_gtr_3,
        deviceSource = 227,
        productionSource = 257,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit GTR 3 Pro CH",
        devicePreview = R.drawable.amazfit_gtr_3,
        deviceSource = 229,
        productionSource = 256,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit GTR 3 Pro GL",
        devicePreview = R.drawable.amazfit_gtr_3,
        deviceSource = 230,
        productionSource = 257,
        application = Application.Zepp,
        region = Region.Default,
    ),
    Device(
        deviceName = "Amazfit GTR 3 Pro Ltd",
        devicePreview = R.drawable.amazfit_gtr_3,
        deviceSource = 242,
        productionSource = 257,
        application = Application.Zepp,
        region = Region.Default,
    ),
)