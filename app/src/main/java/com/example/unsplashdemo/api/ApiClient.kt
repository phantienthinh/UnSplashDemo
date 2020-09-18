package com.example.unsplashdemo.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {
    val BASE_URL_API: String = "https://api.unsplash.com/"

    var retrofit: Retrofit? = null

    companion object {
        private var sInstance: ApiClient? = null
        fun getsInstance(): ApiClient? {
            if (sInstance == null) sInstance = ApiClient()
            return sInstance
        }
    }

    init {
        val httpClient = OkHttpClient.Builder().hostnameVerifier({s, sst->true}).build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_API)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}