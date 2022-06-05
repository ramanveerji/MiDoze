package io.github.keddnyo.midoze.fragments.feed

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import io.github.keddnyo.midoze.R
import io.github.keddnyo.midoze.activities.main.FirmwareActivity
import io.github.keddnyo.midoze.activities.request.RequestActivity
import io.github.keddnyo.midoze.utils.MakeRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.util.*

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.DeviceListViewHolder>(), Filterable {
    private val feedDataArray = ArrayList<FeedData>()
    private var feedDataArrayFull = ArrayList<FeedData>()

    class DeviceListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val deviceNameTextView: TextView =
            itemView.findViewById(R.id.deviceNameTextView)
        val deviceIconImageView: ImageView =
            itemView.findViewById(R.id.deviceIconImageView)
        val firmwareReleaseDateTextView: TextView =
            itemView.findViewById(R.id.firmwareReleaseDateTextView)
        val firmwareChangelogTextView: TextView =
            itemView.findViewById(R.id.firmwareChangelogTextView)

        val likeIcon: ImageView = itemView.findViewById(R.id.favorite_icon)
        val downloadLayout: MaterialCardView = itemView.findViewById(R.id.downloadLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main_firmware, parent, false)
        return DeviceListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeviceListViewHolder, position: Int) {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(holder.deviceNameTextView.context)
        val editor = prefs.edit()

        holder.deviceNameTextView.text = feedDataArray[position].deviceName
        holder.deviceIconImageView.setImageResource(feedDataArray[position].deviceIcon)
        holder.firmwareReleaseDateTextView.text = feedDataArray[position].firmwareReleaseDate
        holder.firmwareChangelogTextView.text = feedDataArray[position].firmwareChangelog

        val deviceIndex = feedDataArray[position].deviceIndex.toString()

        if (prefs.getBoolean(deviceIndex, false)) {
            holder.likeIcon.setImageResource(R.drawable.ic_favorite)
        } else {
            holder.likeIcon.setImageResource(R.drawable.ic_unfavorite)
        }

        holder.likeIcon.setOnClickListener {
            if (prefs.getBoolean(deviceIndex, false)) {
                holder.likeIcon.setImageResource(R.drawable.ic_unfavorite)
                editor.putBoolean(deviceIndex, false)
                editor.apply()
            } else {
                holder.likeIcon.setImageResource(R.drawable.ic_favorite)
                editor.putBoolean(deviceIndex, true)
                editor.apply()
            }
        }

        holder.downloadLayout.setOnClickListener {
            when (MakeRequest().getOnlineState(holder.downloadLayout.context)) {
                true -> {
                    openFirmwareActivity(feedDataArray[position].deviceIndex, holder.downloadLayout.context, false)
                }
                else -> {
                    Toast.makeText(holder.deviceNameTextView.context, R.string.firmware_connectivity_error, Toast.LENGTH_SHORT).show()
                }
            }
        }

        holder.downloadLayout.setOnLongClickListener {
            when (MakeRequest().getOnlineState(holder.downloadLayout.context)) {
                true -> {
                    openFirmwareActivity(feedDataArray[position].deviceIndex, holder.downloadLayout.context, true)
                }
                else -> {
                }
            }
            true
        }
    }

    override fun getItemCount(): Int {
        return feedDataArray.size
    }

    fun addDevice(feedData: FeedData) {
        feedDataArray.add(feedData)
        feedDataArrayFull = ArrayList(feedDataArray)
    }

    override fun getFilter(): Filter {
        return deviceFilter
    }

    private val deviceFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<FeedData> = ArrayList()
            if (constraint.isEmpty()) {
                filteredList.addAll(feedDataArrayFull)
            } else {
                val filterPattern = constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                for (item in feedDataArrayFull) {
                    if (item.deviceName.lowercase(Locale.getDefault()).contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            clear()
            feedDataArray.addAll(results.values as Collection<FeedData>)
            notifyDataSetChanged()
        }
    }

    private fun openFirmwareActivity(deviceIndex: Int, context: Context, custom: Boolean) {
        runBlocking {
            withContext(Dispatchers.IO) {
                val jsonObject = JSONObject(MakeRequest().getApplicationValues())

                val deviceNameValue =
                    jsonObject.getJSONObject(deviceIndex.toString()).getString("name")
                val productionSourceValue =
                    jsonObject.getJSONObject(deviceIndex.toString()).getString("productionSource")
                val appNameValue =
                    jsonObject.getJSONObject(deviceIndex.toString()).getString("appname")
                val appVersionValue =
                    jsonObject.getJSONObject(deviceIndex.toString()).getString("appVersion")

                val intent = if (custom) {
                    Intent(context, RequestActivity::class.java)
                } else {
                    Intent(context, FirmwareActivity::class.java)
                }

                intent.putExtra("deviceName", deviceNameValue)
                intent.putExtra("productionSource", productionSourceValue)
                intent.putExtra("deviceSource", deviceIndex)
                intent.putExtra("appname", appNameValue)
                intent.putExtra("appVersion", appVersionValue)

                context.startActivity(intent)
            }
        }
    }

    fun clear() {
        feedDataArray.clear()
    }
}