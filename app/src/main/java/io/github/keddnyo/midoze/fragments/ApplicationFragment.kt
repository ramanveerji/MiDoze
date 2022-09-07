package io.github.keddnyo.midoze.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.keddnyo.midoze.R
import io.github.keddnyo.midoze.adapters.menu.AppsAdapter
import io.github.keddnyo.midoze.local.menu.Dimens
import io.github.keddnyo.midoze.local.packages.PackageRepository
import io.github.keddnyo.midoze.utils.Display

class ApplicationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_application,
        container,
        false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(requireActivity()) {
        super.onViewCreated(view, savedInstanceState)

        findViewById<RecyclerView>(R.id.appsRecyclerView).let { RecyclerView ->
            RecyclerView.layoutManager = GridLayoutManager(
                context, Display()
                    .getGridLayoutIndex(this, Dimens.CARD_GRID_WIDTH)
            )

            AppsAdapter().let { adapter ->
                RecyclerView.adapter = adapter

                adapter.addItems(
                    PackageRepository().packages
                )
            }
        }
    }
}