package com.example.unsplashdemo.api.objUnSplash.gson.search_image

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tag {
    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("source")
    @Expose
    var source: Source? = null
}