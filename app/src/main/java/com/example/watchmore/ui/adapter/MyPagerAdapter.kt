package com.example.watchmore.ui.adapter

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide


class MyPagerAdapter(context: Context, private val photos: ArrayList<String>, dialog: Dialog) :
    PagerAdapter() {
    private val context: Context
    private val dialog: Dialog
    override fun getCount(): Int {
        return photos.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        val iv = ImageView(context)
        Glide.with(context).load(photos[position])
            .into(iv)
        iv.setOnClickListener(View.OnClickListener() {
            fun onClick(v: View?) {
                dialog.dismiss()
            }
        })
        container.addView(iv)
        return iv
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        container.removeView(`object` as View)
    }

    init {
        this.context = context
        this.dialog = dialog
    }
}