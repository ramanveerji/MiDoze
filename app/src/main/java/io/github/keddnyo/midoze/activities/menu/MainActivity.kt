package io.github.keddnyo.midoze.activities.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.keddnyo.midoze.R
import io.github.keddnyo.midoze.adapters.menu.MenuAdapter
import io.github.keddnyo.midoze.local.menu.MenuRepository
import io.github.keddnyo.midoze.remote.Updates
import io.github.keddnyo.midoze.utils.AndroidVersion
import io.github.keddnyo.midoze.utils.Display

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.menuRecyclerView).let { RecyclerView ->
            RecyclerView.layoutManager = GridLayoutManager(
                this, Display()
                    .getGridLayoutIndex(this, 350)
            )

            this@MainActivity.let { context ->
                MenuAdapter().let { adapter ->
                    RecyclerView.adapter = adapter

                    adapter.addItems(
                        MenuRepository(context).items
                    )
                }

                if (AndroidVersion().isLollipopOrAbove) {
                    Updates(context).execute()
                }
            }
        }
    }
}