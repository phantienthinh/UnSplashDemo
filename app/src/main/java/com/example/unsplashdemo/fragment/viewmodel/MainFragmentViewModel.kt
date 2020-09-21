package com.example.unsplashdemo.fragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.unsplashdemo.api.objUnSplash.UnSplash
import com.example.unsplashdemo.fragment.repository.UnsplashRepository
import kotlinx.coroutines.flow.Flow

class MainFragmentViewModel(
    private val repository: UnsplashRepository
) : ViewModel() {
    private var currentQuery: String? = null
    private var currentSearchResult: Flow<PagingData<UnSplash>>? = null

    fun getListDataImageUnsplash(): LiveData<PagingData<UnSplash>> {
        return repository.getImageResultStream().cachedIn(viewModelScope)
    }

//    fun searchUnsplachImage(queryString: String): Flow<PagingData<UnSplash>>? {
//        val lastResult = currentSearchResult
//        if (queryString == currentQuery && lastResult != null) {
//            return lastResult
//        }
//        currentQuery = queryString
//        return repository.getImageResultStream()
//    }


}