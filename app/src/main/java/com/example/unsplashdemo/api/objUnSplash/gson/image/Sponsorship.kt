package com.example.unsplashdemo.api.objUnSplash.gson.image

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Sponsorship {
    @SerializedName("impression_urls")
    @Expose
    var impressionUrls: List<Any>? = null

    @SerializedName("tagline")
    @Expose
    var tagline: String? = null

    @SerializedName("tagline_url")
    @Expose
    var taglineUrl: Any? = null

    @SerializedName("sponsor")
    @Expose
    var sponsor: Sponsor? = null
}