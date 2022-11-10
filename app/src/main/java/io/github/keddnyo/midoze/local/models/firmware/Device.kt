package io.github.keddnyo.midoze.local.models.firmware

import io.github.keddnyo.midoze.R
import io.github.keddnyo.midoze.local.repository.Region

open class Device(
    val deviceName: String,
    val devicePreview: Int,
    val deviceSource: Int,
    val productionSource: Int,
    val application: Application,
    val region: Region,
) {
    object XiaomiMiBand3 : Device(
        deviceName = "Xiaomi Mi Band 3",
        devicePreview = R.drawable.mi_band_3i,
        deviceSource = 31,
        productionSource = 256,
        application = Application.ZeppLife,
        region = Region.UnitedStates,
    )

    object XiaomiMiBand4GL : Device(
        deviceName = "Xiaomi Mi Band 4 GL",
        devicePreview = R.drawable.ic_zepp_life,
        deviceSource = 25,
        productionSource = 257,
        application = Application.ZeppLife,
        region = Region.UnitedStates,
    )

    object XiaomiMiBand4NFC : Device(
        deviceName = "Xiaomi Mi Band 4 NFC",
        devicePreview = R.drawable.ic_zepp_life,
        deviceSource = 24,
        productionSource = 256,
        application = Application.ZeppLife,
        region = Region.UnitedStates,
    )

    object XiaomiMiBand5 : Device(
        deviceName = "Xiaomi Mi Band 5",
        devicePreview = R.drawable.ic_zepp_life,
        deviceSource = 59,
        productionSource = 257,
        application = Application.ZeppLife,
        region = Region.UnitedStates,
    )

    object XiaomiMiBand5NFC : Device(
        deviceName = "Xiaomi Mi Band 5 NFC",
        devicePreview = R.drawable.ic_zepp_life,
        deviceSource = 58,
        productionSource = 256,
        application = Application.ZeppLife,
        region = Region.UnitedStates,
    )

    object XiaomiMiBand6GL : Device(
        deviceName = "Xiaomi Mi Band 6 GL",
        devicePreview = R.drawable.ic_zepp_life,
        deviceSource = 212,
        productionSource = 257,
        application = Application.ZeppLife,
        region = Region.UnitedStates,
    )

    object XiaomiMiBand6NFC : Device(
        deviceName = "Xiaomi Mi Band 6 NFC",
        devicePreview = R.drawable.ic_zepp_life,
        deviceSource = 211,
        productionSource = 256,
        application = Application.ZeppLife,
        region = Region.UnitedStates,
    )

    object XiaomiMiBand7var1 : Device(
        deviceName = "Xiaomi Mi Band 7",
        devicePreview = R.drawable.ic_zepp_life,
        deviceSource = 260,
        productionSource = 256,
        application = Application.ZeppLife,
        region = Region.UnitedStates,
    )

    object XiaomiMiBand7var2 : Device(
        deviceName = "Xiaomi Mi Band 7",
        devicePreview = R.drawable.ic_zepp_life,
        deviceSource = 262,
        productionSource = 256,
        application = Application.ZeppLife,
        region = Region.UnitedStates,
    )

    object XiaomiMiBand7var3 : Device(
        deviceName = "Xiaomi Mi Band 7",
        devicePreview = R.drawable.ic_zepp_life,
        deviceSource = 263,
        productionSource = 256,
        application = Application.ZeppLife,
        region = Region.UnitedStates,
    )

    object XiaomiMiBand7var4 : Device(
        deviceName = "Xiaomi Mi Band 7",
        devicePreview = R.drawable.ic_zepp_life,
        deviceSource = 211,
        productionSource = 264,
        application = Application.ZeppLife,
        region = Region.UnitedStates,
    )

    object XiaomiMiBand7var5 : Device(
        deviceName = "Xiaomi Mi Band 7",
        devicePreview = R.drawable.ic_zepp_life,
        deviceSource = 211,
        productionSource = 265,
        application = Application.ZeppLife,
        region = Region.UnitedStates,
    )
}

val deviceList = arrayListOf(
    Device.XiaomiMiBand3,
    Device.XiaomiMiBand4GL,
    Device.XiaomiMiBand4NFC,
    Device.XiaomiMiBand5,
    Device.XiaomiMiBand5NFC,
    Device.XiaomiMiBand6GL,
    Device.XiaomiMiBand6NFC,
    Device.XiaomiMiBand7var1,
    Device.XiaomiMiBand7var2,
    Device.XiaomiMiBand7var3,
    Device.XiaomiMiBand7var4,
    Device.XiaomiMiBand7var5,
)