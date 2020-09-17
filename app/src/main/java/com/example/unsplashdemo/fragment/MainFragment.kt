package com.example.unsplashdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.unsplashdemo.R
import com.example.unsplashdemo.databinding.FragmentMainBinding

class MainFragment : Fragment(), View.OnClickListener {
    lateinit var mainFragmentBinding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return mainFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainFragmentBinding.fragmentMainDetailBt.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            mainFragmentBinding.fragmentMainDetailBt -> {
                findNavController().navigate(R.id.action_fragment_main_to_detailFragment)
            }
        }
    }
}