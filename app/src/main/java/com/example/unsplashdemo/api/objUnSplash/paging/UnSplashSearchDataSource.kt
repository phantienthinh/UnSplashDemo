package com.example.unsplashdemo.api.objUnSplash.paging

import androidx.paging.PagingSource
import com.example.unsplashdemo.api.ApiServer
import com.example.unsplashdemo.api.objUnSplash.gson.search_image.Result

class UnSplashSearchDataSource(val apiServer: ApiServer, val query: String) :
    PagingSource<Int, Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        try {
            var currenPage = params.key ?: 1
            var listSearchData = mutableListOf<Result>()
            var dataResponse = apiServer.searchImage(query, currenPage, 30)
            var list = dataResponse.body()?.photos
            listSearchData.addAll(list?.results!!)

            val prevKey = if (currenPage == 1) null else currenPage - 1

            return LoadResult.Page(
                data = listSearchData,
                prevKey = prevKey,
                nextKey = currenPage.plus(1)
            )

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}