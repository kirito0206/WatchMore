package com.example.watchmore.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.example.watchmore.R
import com.example.watchmore.databinding.ItemAnimeGridLayoutBinding
import com.example.watchmore.model.bean.animebean.AnimeData
import com.example.watchmore.util.debug
import com.example.watchmore.viewmodel.CommonViewModel

class GridAdapter(private val animesList:MutableList<AnimeData>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var binding = DataBindingUtil.inflate<ItemAnimeGridLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_anime_grid_layout,
            parent,
            false
        )
        var data = CommonViewModel()
        binding.data = data
        binding.anime = animesList[position]
        debug("position + $position")
        return binding.root
    }

    override fun getItem(position: Int): Any {
        return animesList[position]
    }

    override fun getItemId(position: Int): Long = position.toLong()
    override fun getCount(): Int = animesList.size
}