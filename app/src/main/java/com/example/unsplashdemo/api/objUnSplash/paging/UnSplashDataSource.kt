package com.example.unsplashdemo.api.objUnSplash.paging

import android.util.Log
import androidx.paging.PagingSource
import com.example.unsplashdemo.api.ApiServer
import com.example.unsplashdemo.api.objUnSplash.UnSplash


class UnSplashDataSource(private val apiServer: ApiServer) : PagingSource<Int, UnSplash>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnSplash> {
        try {
            val responseData = mutableListOf<UnSplash>()
            val currentLoadingKey = params.key ?: 1

            val response = apiServer.getListImage(currentLoadingKey, 5)
            val completed = response.body()
            responseData.addAll(completed!!)

            val prevKey = if (currentLoadingKey == 1) null else currentLoadingKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingKey.plus(1)
            )
        } catch (e: Exception) {
            Log.e(TAG, "load: ", e)
            return LoadResult.Error(e)
        }
    }

    companion object {
        private const val TAG = "UnSplashDataSource"
    }
}