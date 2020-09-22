package com.example.unsplashdemo.api.objUnSplash.gson.search_image

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Links__ {
    @SerializedName("self")
    @Expose
    var self: String? = null

    @SerializedName("html")
    @Expose
    var html: String? = null

    @SerializedName("download")
    @Expose
    var download: String? = null

    @SerializedName("download_location")
    @Expose
    var downloadLocation: String? = null
}