package com.example.unsplashdemo.api.objUnSplash.gson.search_image

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Links____ {
    @SerializedName("self")
    @Expose
    var self: String? = null

    @SerializedName("html")
    @Expose
    var html: String? = null

    @SerializedName("photos")
    @Expose
    var photos: String? = null

    @SerializedName("related")
    @Expose
    var related: String? = null
}