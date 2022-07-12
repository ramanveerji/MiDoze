package io.github.keddnyo.midoze.activities.main

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.preference.PreferenceManager
import androidx.viewpager.widget.ViewPager
import io.github.keddnyo.midoze.R
import io.github.keddnyo.midoze.activities.request.RequestActivity
import io.github.keddnyo.midoze.databinding.ActivityMainBinding
import io.github.keddnyo.midoze.fragments.FeedFragment
import io.github.keddnyo.midoze.fragments.SettingsFragment
import io.github.keddnyo.midoze.remote.AppUpdates
import io.github.keddnyo.midoze.utils.Display
import io.github.keddnyo.midoze.utils.Language
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager
    val context = this@MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        Display().switchDarkMode(context)
        super.onCreate(savedInstanceState)

        if (android.os.Build.VERSION.SDK_INT >= 21) {

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val adapter = MyAdapter(this, supportFragmentManager)

            viewPager = findViewById(R.id.viewPager)
            viewPager.adapter = adapter

            val bottomBar = binding.bottomBar

            viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int,
                ) {
                }

                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> {
                            bottomBar.menu.findItem(R.id.menu_feed).isChecked = true
                            title = getString(R.string.feed_title)
                        }
                        1 -> {
                            bottomBar.menu.findItem(R.id.menu_settings).isChecked = true
                            title = getString(R.string.settings_title)
                        }
                    }
                }
            })

            bottomBar.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_feed -> viewPager.currentItem = 0
                    R.id.menu_settings -> viewPager.currentItem = 1
                    else -> viewPager.currentItem = 0
                }
                true
            }

            // Default tab
            viewPager.currentItem = 0
            title = getString(R.string.feed_title)

            val prefs = PreferenceManager.getDefaultSharedPreferences(this)

            AppUpdates(prefs, context).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        } else {
            finish()
            startActivity(Intent(this, RequestActivity::class.java))
            Display().showToast(context, getString(R.string.compatibility_mode))
        }
    }

    override fun onResume() {
        super.onResume()

        fun getCurrentLocale(): Locale {
            return Language().getCurrent()
        }

        fun setLocale(loc: Locale) {
            val config = Configuration()
            config.locale = loc
            baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        }

        val ruLocale = Locale("ru")
        val beLocale = Locale("be")
        val uaLocale = Locale("uk")
        val enLocale = Locale("en")

        when (getCurrentLocale()) {
            ruLocale -> {
                Locale.setDefault(ruLocale)
                setLocale(ruLocale)
            }
            beLocale -> {
                Locale.setDefault(ruLocale)
                setLocale(ruLocale)
            }
            uaLocale -> {
                Locale.setDefault(ruLocale)
                setLocale((ruLocale))
            }
            else -> {
                Locale.setDefault(enLocale)
                setLocale(enLocale)
            }
        }
    }

    class MyAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> FeedFragment()
                1 -> SettingsFragment()
                else -> FeedFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return context.resources.getString(Display().tabTitles[position])
        }
    }

    override fun onBackPressed() {
        if (viewPager.currentItem != 0) {
            viewPager.currentItem = 0
        } else {
            super.onBackPressed()
        }
    }
}