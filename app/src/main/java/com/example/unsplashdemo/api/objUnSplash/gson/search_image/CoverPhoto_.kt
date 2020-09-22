package com.example.unsplashdemo.api.objUnSplash.gson.search_image

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CoverPhoto_ {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("promoted_at")
    @Expose
    var promotedAt: String? = null

    @SerializedName("width")
    @Expose
    var width: Int? = null

    @SerializedName("height")
    @Expose
    var height: Int? = null

    @SerializedName("color")
    @Expose
    var color: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("alt_description")
    @Expose
    var altDescription: String? = null

    @SerializedName("urls")
    @Expose
    var urls: Urls__? = null

    @SerializedName("links")
    @Expose
    var links: Links______? = null

    @SerializedName("categories")
    @Expose
    var categories: List<Any>? = null

    @SerializedName("likes")
    @Expose
    var likes: Int? = null

    @SerializedName("liked_by_user")
    @Expose
    var likedByUser: Boolean? = null

    @SerializedName("current_user_collections")
    @Expose
    var currentUserCollections: List<Any>? = null

    @SerializedName("sponsorship")
    @Expose
    var sponsorship: Any? = null

    @SerializedName("user")
    @Expose
    var user: User___? = null
}