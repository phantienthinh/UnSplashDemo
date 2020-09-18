package com.example.unsplashdemo.fragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.unsplashdemo.api.ApiServer
import com.example.unsplashdemo.api.objUnSplash.paging.UnSplashDataSource

class MainFragmentViewModel(private val apiService: ApiServer) : ViewModel() {
    val listData = Pager(PagingConfig(pageSize = 30)) {
        UnSplashDataSource(apiService)
    }.flow.cachedIn(viewModelScope)
}