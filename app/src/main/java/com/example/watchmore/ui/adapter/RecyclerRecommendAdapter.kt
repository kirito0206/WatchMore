package com.example.watchmore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.watchmore.R
import com.example.watchmore.databinding.ItemRecommendRecyclerLayoutBinding
import com.example.watchmore.model.bean.recommendbean.RecommendBean
import com.example.watchmore.model.bean.userbean.UserBean
import com.example.watchmore.viewmodel.CommonViewModel

class RecyclerRecommendAdapter(private var recommendList: MutableList<RecommendBean>, private var usersList: MutableList<UserBean>) : RecyclerView.Adapter<RecyclerRecommendAdapter.ViewHolder>() {

    override fun getItemCount(): Int  = recommendList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemRecommendRecyclerLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_recommend_recycler_layout,
            parent,
            false
        )
        return ViewHolder(binding)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recommendList[position],usersList[position])    }

    class ViewHolder(private val binding: ItemRecommendRecyclerLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(recommend: RecommendBean, user : UserBean) {
            //方法一：
//            binding.setVariable(BR.user,data)
            //方法二：
            binding.recommend = recommend
            binding.user = user
            binding.data = CommonViewModel()
            binding.executePendingBindings()
        }
    }
}