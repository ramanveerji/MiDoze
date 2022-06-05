package io.github.keddnyo.midoze.fragments

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.AsyncTask
import android.os.Bundle
import android.os.Parcelable
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.github.keddnyo.midoze.R
import io.github.keddnyo.midoze.utils.MakeRequest
import io.github.keddnyo.midoze.utils.StringUtils
import io.github.keddnyo.midoze.utils.UiUtils
import io.github.keddnyo.midoze.fragments.feed.FeedAdapter
import io.github.keddnyo.midoze.fragments.feed.FeedData
import org.json.JSONArray
import org.json.JSONObject

class FeedFragment : Fragment() {

    private val deviceListIndex = hashMapOf<String, Int>()
    private val feedAdapter = FeedAdapter()
    private lateinit var deviceListRecyclerView: RecyclerView
    private lateinit var prefs: SharedPreferences
    private var state: Parcelable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(requireActivity()) {
        super.onViewCreated(view, savedInstanceState)
        val context = requireContext()

        val firmwaresRefreshLayout: SwipeRefreshLayout = findViewById(R.id.firmwaresRefreshLayout)

        val firmwaresProgressBar: ProgressBar = findViewById(R.id.firmwaresProgressBar)
        val firmwaresErrorMessage: ConstraintLayout = findViewById(R.id.firmwaresErrorMessage)

        deviceListRecyclerView = findViewById(R.id.deviceListRecyclerView)
        deviceListRecyclerView.layoutManager =
            GridLayoutManager(this, UiUtils().getGridLayoutIndex(this, 400))

        val adapter = feedAdapter
        deviceListRecyclerView.adapter = adapter

        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = prefs.edit()

        class LoadDataForActivity :
            AsyncTask<Void?, Void?, Void>() {
            var firmwaresData: JSONObject = JSONObject("{}")
            val preloadedFirmwares = prefs.getString("Firmwares", "").toString()

            @Deprecated("Deprecated in Java")
            override fun onPreExecute() {
                super.onPreExecute()
                firmwaresProgressBar.visibility = View.VISIBLE
                firmwaresErrorMessage.visibility = View.GONE
            }

            @Deprecated("Deprecated in Java")
            override fun doInBackground(vararg p0: Void?): Void? {

                fun getOnlineState(): Boolean {
                    return MakeRequest().getOnlineState(context)
                }

                fun getFirmwaresData() {
                    if (getOnlineState()) {
                        firmwaresData = MakeRequest().getFirmwareLatest()
                        editor.putString("Firmwares", firmwaresData.toString())
                        editor.apply()
                    } else {
                        runOnUiThread {
                            firmwaresProgressBar.visibility = View.GONE
                            firmwaresErrorMessage.visibility = View.VISIBLE
                        }
                    }
                }

                if (prefs.getBoolean("settings_feed_cache_use", true) && preloadedFirmwares != "") {
                    firmwaresData = JSONObject(preloadedFirmwares)
                } else {
                    getFirmwaresData()
                }

                return null
            }

            @Deprecated("Deprecated in Java")
            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)

                fun getData(favorite: Boolean) {
                    if (firmwaresData != JSONObject("{}")) {

                        val responseParamsArray = firmwaresData.toMap()
                        val keys = responseParamsArray.keys

                        for (i in keys) {
                            val jsonObject = firmwaresData.getJSONObject(i)

                            val deviceNameValue = jsonObject.getString("name")
                            val deviceIconValue = when {
                                deviceNameValue.contains(getString(R.string.title_mi_band),
                                    true) -> {
                                    R.drawable.ic_xiaomi
                                }
                                deviceNameValue.contains(getString(R.string.title_zepp), true) -> {
                                    R.drawable.ic_zepp
                                }
                                else -> {
                                    R.drawable.ic_amazfit
                                }
                            }
                            val firmwareVersionValue = jsonObject.getString("fw")
                            val firmwareReleaseDateValue =
                                StringUtils().getLocalFirmwareDate(jsonObject.getString("date"))

                            val firmwareVersion = getString(R.string.firmware_version)
                            val firmwareChangelogValue = "$firmwareVersion: $firmwareVersionValue"

                            if (prefs.getBoolean(i, false) == favorite) {
                                feedAdapter.addDevice(
                                    FeedData(
                                        deviceNameValue,
                                        deviceIconValue,
                                        firmwareReleaseDateValue,
                                        firmwareChangelogValue,
                                        i.toInt()
                                    )
                                )
                                feedAdapter.notifyItemInserted(i.toInt())
                                deviceListIndex[deviceNameValue] = i.toInt()
                            }
                        }
                    }
                }
                firmwaresProgressBar.visibility = View.GONE
                firmwaresRefreshLayout.isRefreshing = false
                getData(true)
                getData(false)

                if (state != null) {
                    deviceListRecyclerView.layoutManager?.onRestoreInstanceState(state)
                }
            }
        }

        @SuppressLint("NotifyDataSetChanged")
        fun setData() {
            feedAdapter.clear()
            feedAdapter.notifyDataSetChanged()
            LoadDataForActivity().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }

        if (FeedAdapter().itemCount == 0) {
            setData()
        }

        firmwaresRefreshLayout.setOnRefreshListener {
            setData()
        }
    }

    override fun onPause() {
        super.onPause()
        state = deviceListRecyclerView.layoutManager?.onSaveInstanceState()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_feed, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = searchItem?.actionView as SearchView

        searchView.imeOptions = EditorInfo.IME_ACTION_DONE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                feedAdapter.filter.filter(newText)
                return false
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        feedAdapter.clear()
    }

    private fun JSONObject.toMap(): Map<String, *> = keys().asSequence().associateWith { it ->
        when (val value = this[it]) {
            is JSONArray -> {
                val map = (0 until value.length()).associate { Pair(it.toString(), value[it]) }
                JSONObject(map).toMap().values.toList()
            }
            is JSONObject -> value.toMap()
            JSONObject.NULL -> null
            else -> value
        }
    }
}