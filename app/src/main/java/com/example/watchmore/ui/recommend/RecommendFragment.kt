package com.example.watchmore.ui.recommend

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
import com.example.watchmore.databinding.FragmentRecommendBinding

class RecommendFragment : Fragment() {

    private lateinit var recommendBinding : FragmentRecommendBinding
    private lateinit var recommendViewModel: RecommendViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        if (savedInstanceState == null){
            recommendViewModel =
                ViewModelProviders.of(this).get(RecommendViewModel::class.java)
            recommendBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_recommend,container,false)
            recommendBinding.data = recommendViewModel
            recommendBinding.lifecycleOwner = this
        }
        return recommendBinding.root
    }
}
