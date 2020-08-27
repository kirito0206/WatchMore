package com.example.watchmore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.watchmore.R
import com.example.watchmore.databinding.ItemCommentRecyclerLayoutBinding
import com.example.watchmore.model.bean.animebean.Comment
import com.example.watchmore.ui.activity.AnimeDetailActivity


class RecyclerCommentAdapter (private var commentsList: MutableList<Comment>) : RecyclerView.Adapter<RecyclerCommentAdapter.ViewHolder>() {

    override fun getItemCount(): Int  = commentsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemCommentRecyclerLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_comment_recycler_layout,
            parent,
            false
        )
        return ViewHolder(binding)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commentsList[position])    }

    class ViewHolder(private val binding: ItemCommentRecyclerLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            //方法一：
//            binding.setVariable(BR.user,data)
            //方法二：
            binding.comment = comment
            binding.data = AnimeDetailActivity.getViewModel()
            binding.executePendingBindings()
        }
    }
}