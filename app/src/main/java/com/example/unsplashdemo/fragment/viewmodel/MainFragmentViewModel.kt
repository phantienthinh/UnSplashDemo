package com.example.unsplashdemo.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.unsplashdemo.api.objUnSplash.UnSplash
import com.example.unsplashdemo.api.objUnSplash.gson.search_image.Result
import com.example.unsplashdemo.fragment.repository.UnsplashRepository

class MainFragmentViewModel(
    private val repository: UnsplashRepository
) : ViewModel() {
    private var currentQuery: String? = null
    private var currentSearchResult: LiveData<PagingData<Result>>? = null

    fun getListDataImageUnsplash(): LiveData<PagingData<UnSplash>> {
        return repository.getImageResultStream().cachedIn(viewModelScope)
    }

    fun searchUnSplashImage(queryString: String): LiveData<PagingData<Result>>? {
        val lastResult = currentSearchResult
        if (queryString == currentQuery && lastResult != null) {
            return lastResult
        }
        currentQuery = queryString
        return repository.getSearchImageResult(queryString).cachedIn(viewModelScope)
    }

}