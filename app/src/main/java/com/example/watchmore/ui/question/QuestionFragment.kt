package com.example.watchmore.ui.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.watchmore.R
import com.example.watchmore.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {

    private lateinit var questionBinding : FragmentQuestionBinding
    private lateinit var questionViewModel: QuestionViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        if (savedInstanceState == null){
            questionViewModel =
                ViewModelProviders.of(this).get(QuestionViewModel::class.java)
            questionBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_question,container,false)
            questionBinding.data = questionViewModel
            questionBinding.lifecycleOwner = this
        }
        return questionBinding.root
    }
}
