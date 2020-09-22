package com.example.unsplashdemo.fragment.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.unsplashdemo.api.ApiServer
import com.example.unsplashdemo.api.objUnSplash.UnSplash
import com.example.unsplashdemo.api.objUnSplash.gson.search_image.Result
import com.example.unsplashdemo.api.objUnSplash.paging.UnSplashDataSource
import com.example.unsplashdemo.api.objUnSplash.paging.UnSplashSearchDataSource

class UnsplashRepository(private val apiServer: ApiServer) {
    fun getImageResultStream(): LiveData<PagingData<UnSplash>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ), pagingSourceFactory = { UnSplashDataSource(apiServer) }
        ).liveData
    }

    fun getSearchImageResult(query: String): LiveData<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ), pagingSourceFactory = { UnSplashSearchDataSource(apiServer, query) }
        ).liveData
    }

    companion object {
        private const val PAGE_SIZE = 30
    }
}