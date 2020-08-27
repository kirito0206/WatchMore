package com.example.watchmore.ui.search

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.watchmore.R
import com.example.watchmore.databinding.FragmentSearchBinding
import com.example.watchmore.model.bean.animebean.AnimeData
import com.zhouwei.mzbanner.holder.MZHolderCreator
import com.zhouwei.mzbanner.holder.MZViewHolder
import java.util.Observer


class SearchFragment : Fragment() {

    private lateinit var searchBinding : FragmentSearchBinding
    private lateinit var searchViewModel: SearchViewModel
    private var timeLinearLayoutList : ArrayList<LinearLayout> = ArrayList()
    private var timeTagList : ArrayList<TextView> = ArrayList()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        if (savedInstanceState == null){
            searchViewModel =
                ViewModelProviders.of(this).get(SearchViewModel::class.java)
            searchBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_search,container,false)
            searchBinding.data = searchViewModel
            searchBinding.lifecycleOwner = this
            initMZBannerView()
            initView()
            initTagView()
        }
        return searchBinding.root
    }

    private fun initMZBannerView(){
        searchBinding.banner.setPages(searchViewModel.getImageList(),
            MZHolderCreator<BannerViewHolder> { BannerViewHolder() })
    }

    private fun initView(){
        searchBinding.tagView.setOnClickListener(View.OnClickListener {
            searchBinding.drawerLayout.openDrawer(GravityCompat.START)
        })
    }
    
    private fun initTagView(){
        timeLinearLayoutList.add(searchBinding.timeTagOne)
        timeLinearLayoutList.add(searchBinding.timeTagTwo)
        timeLinearLayoutList.add(searchBinding.timeTagThree)
        timeLinearLayoutList.add(searchBinding.timeTagFour)
        timeLinearLayoutList.add(searchBinding.timeTagFive)
        var timeList = searchViewModel.getTimeTagMap().toList()
        for ((i, linearView) in timeLinearLayoutList.withIndex()){
            for (k in i*3..i*3+2){
                if (k < timeList.size){
                    var textView = TextView(context)
                    textView.text = timeList[k].first
                    val lp: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    lp.setMargins(32,32,32,32)
                    textView.setTextColor(Color.BLACK)
                    textView.isClickable = true
                    textView.layoutParams = lp
                    textView.setOnClickListener(View.OnClickListener {
                        changeColor(timeTagList,textView)
                    })
                    linearView.addView(textView)
                    timeTagList.add(textView)
                }
            }
        }
    }

    private fun changeColor(list: ArrayList<TextView>, textView: TextView) {
        for (tv in list){
            tv.setTextColor(Color.BLACK)
        }
        textView.setTextColor(R.attr.colorAccent)
    }

    class BannerViewHolder : MZViewHolder<Int?> {
        private var mImageView: ImageView? = null
        override fun createView(context: Context?): View {
            // 返回页面布局
            val view: View =
                LayoutInflater.from(context).inflate(R.layout.banner_item, null)
            mImageView = view.findViewById<View>(R.id.banner_image) as ImageView
            return view
        }

        override fun onBind(context: Context?, position: Int, data: Int?) {
            // 数据绑定
            if (data != null) {
                mImageView?.setImageResource(data)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        searchBinding.banner.pause()
    }

    override fun onResume() {
        super.onResume()
        searchBinding.banner.start()
    }
}
