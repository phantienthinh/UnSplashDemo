package com.example.unsplashdemo.api.objUnSplash.gson.search_image

import com.example.unsplashdemo.api.objUnSplash.gson.search_image.Collections
import com.example.unsplashdemo.api.objUnSplash.gson.search_image.Meta
import com.example.unsplashdemo.api.objUnSplash.gson.search_image.Photos
import com.example.unsplashdemo.api.objUnSplash.gson.search_image.Users
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataSearch {
    @SerializedName("photos")
    @Expose
    var photos: Photos? = null

    @SerializedName("collections")
    @Expose
    var collections: Collections? = null

    @SerializedName("users")
    @Expose
    var users: Users? = null

    @SerializedName("related_searches")
    @Expose
    var relatedSearches: List<Any>? = null

    @SerializedName("meta")
    @Expose
    var meta: Meta? = null
}