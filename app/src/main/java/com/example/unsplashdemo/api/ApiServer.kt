package com.example.unsplashdemo.api

import com.example.unsplashdemo.api.objUnSplash.UnSplash
import com.example.unsplashdemo.api.objUnSplash.gson.search_image.DataSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServer {

    @GET("photos/?client_id=EbOOHuUeFzALb__WYeQdQ_diRVEWzNIyNHQmm1cGu3o")
    suspend fun getListImage(
        @Query("page") page: Int,
        @Query("per_page") number: Int
    ): Response<ArrayList<UnSplash>>

    @GET("search/?client_id=EbOOHuUeFzALb__WYeQdQ_diRVEWzNIyNHQmm1cGu3o")
    suspend fun searchImage(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") number: Int
    ): Response<DataSearch>

    companion object {
        fun getApiServer(): ApiServer? {
            val retrofit = ApiClient.getsInstance()!!.retrofit
            return retrofit!!.create(ApiServer::class.java)
        }
    }
}