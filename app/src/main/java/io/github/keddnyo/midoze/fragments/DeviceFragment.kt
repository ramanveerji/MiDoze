package io.github.keddnyo.midoze.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import io.github.keddnyo.midoze.R
import io.github.keddnyo.midoze.activities.main.adapters.DeviceAdapter
import io.github.keddnyo.midoze.local.dataModels.FirmwareData
import io.github.keddnyo.midoze.utils.Display

class DeviceFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_device, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) = with(requireActivity()) {
        super.onCreate(savedInstanceState)

        val deviceListRecyclerView: RecyclerView = findViewById(R.id.deviceListRecyclerView)
        deviceListRecyclerView.layoutManager =
            GridLayoutManager(
                this, Display()
                    .getGridLayoutIndex(this, 200)
            )

        val adapter = DeviceAdapter()
        deviceListRecyclerView.adapter = adapter

        val firmwaresDataArray: ArrayList<FirmwareData> = GsonBuilder().create().fromJson(
            arguments?.getString("deviceArray"),
            object : TypeToken<ArrayList<FirmwareData>>() {}.type
        )

        adapter.addDevice(firmwaresDataArray)
    }
}