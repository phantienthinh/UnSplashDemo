package com.example.unsplashdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.unsplashdemo.R
import com.example.unsplashdemo.databinding.FragmentDetailBinding

class DetailFragment : Fragment(), View.OnClickListener {

    lateinit var detailBinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailBinding.fragmentDetailBackBt.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            detailBinding.fragmentDetailBackBt -> {
                findNavController().navigate(R.id.action_detailFragment_to_fragment_main)
            }
        }
    }

}