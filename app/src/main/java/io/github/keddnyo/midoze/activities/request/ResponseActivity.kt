package io.github.keddnyo.midoze.activities.request

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import io.github.keddnyo.midoze.R
import io.github.keddnyo.midoze.databinding.ActivityResponseBinding
import io.github.keddnyo.midoze.utils.StringUtils

class ResponseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResponseBinding
    private var json: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityResponseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.settings_server_response)

        val responseTextView = binding.responseText
        json = StringUtils().cleanResponse(intent.getStringExtra("json").toString())
        responseTextView.setText(json)
        responseTextView.requestFocus()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_response, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share_firmware -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, json)
                    type = "text/plain"
                }

                val shareIntent =
                    Intent.createChooser(sendIntent, getString(R.string.settings_server_response))
                startActivity(shareIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}