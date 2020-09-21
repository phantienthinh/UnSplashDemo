package com.example.unsplashdemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.unsplashdemo.R
import com.example.unsplashdemo.Utils
import com.example.unsplashdemo.api.objUnSplash.UnSplash
import com.example.unsplashdemo.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    lateinit var detailBinding: FragmentDetailBinding
    lateinit var unSplash: UnSplash

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
        unSplash = arguments?.getSerializable(Utils.VALUE_DATA) as UnSplash
        Glide.with(this).load(unSplash.urls?.regular).into(detailBinding.fragmentDetailImage)
    }

}