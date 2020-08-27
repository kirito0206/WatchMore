package com.example.watchmore.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.watchmore.R
import com.example.watchmore.databinding.ItemQuestionRecyclerLayoutBinding
import com.example.watchmore.model.bean.questionbean.QuestionBean
import com.example.watchmore.model.bean.userbean.UserBean

class RecyclerQuestionAdapter(private var questionsList: MutableList<QuestionBean> , private var usersList: MutableList<UserBean>) : RecyclerView.Adapter<RecyclerQuestionAdapter.ViewHolder>() {

    override fun getItemCount(): Int  = questionsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemQuestionRecyclerLayoutBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_question_recycler_layout,
            parent,
            false
        )
        return ViewHolder(binding)    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questionsList[position],usersList[position])    }

    class ViewHolder(private val binding: ItemQuestionRecyclerLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(question: QuestionBean , user : UserBean) {
            //方法一：
//            binding.setVariable(BR.user,data)
            //方法二：
            binding.question = question
            binding.user = user
            binding.executePendingBindings()
        }
    }
}
