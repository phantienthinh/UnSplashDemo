package com.example.unsplashdemo.fragment.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.unsplashdemo.api.ApiServer
import com.example.unsplashdemo.fragment.viewmodel.MainFragmentViewModel

class MainFragmentViewModelFactory(private val apiServer: ApiServer) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainFragmentViewModel::class.java)) {
            return MainFragmentViewModel(apiServer) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}