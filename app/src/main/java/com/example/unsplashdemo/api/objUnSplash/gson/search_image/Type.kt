package com.example.unsplashdemo.api.objUnSplash.gson.search_image

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Type {
    @SerializedName("slug")
    @Expose
    var slug: String? = null

    @SerializedName("pretty_slug")
    @Expose
    var prettySlug: String? = null
}