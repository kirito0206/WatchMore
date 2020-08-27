package com.example.watchmore.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.watchmore.R
import com.example.watchmore.databinding.FragmentUserBinding
import com.example.watchmore.util.debug

class UserFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userFragmentBinding : FragmentUserBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (savedInstanceState == null){
            userViewModel =
                ViewModelProviders.of(this).get(UserViewModel::class.java)
            userFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_user,container,false)
            setHasOptionsMenu(true)
            userFragmentBinding.toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener {
                when (it.itemId){
                    R.id.action_theme -> context?.let { it1 -> userViewModel.intentToTheme(it1) }
                    R.id.action_logout -> context?.let { it1 -> userViewModel.logout(it1) }
                }
                return@OnMenuItemClickListener true
            })
            /*userViewModel.mData.observe(this, Observer<String> {

            })*/
            userFragmentBinding.data = userViewModel
            userFragmentBinding.lifecycleOwner = this
        }
        return userFragmentBinding.root
    }

}

