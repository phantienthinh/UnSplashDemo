package com.example.unsplashdemo.api.objUnSplash.gson.search_image

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result_ {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("description")
    @Expose
    var description: Any? = null

    @SerializedName("published_at")
    @Expose
    var publishedAt: String? = null

    @SerializedName("last_collected_at")
    @Expose
    var lastCollectedAt: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("curated")
    @Expose
    var curated: Boolean? = null

    @SerializedName("featured")
    @Expose
    var featured: Boolean? = null

    @SerializedName("total_photos")
    @Expose
    var totalPhotos: Int? = null

    @SerializedName("private")
    @Expose
    var private: Boolean? = null

    @SerializedName("share_key")
    @Expose
    var shareKey: String? = null

    @SerializedName("tags")
    @Expose
    var tags: List<Tag>? = null

    @SerializedName("links")
    @Expose
    var links: Links____? = null

    @SerializedName("user")
    @Expose
    var user: User__? = null

    @SerializedName("cover_photo")
    @Expose
    var coverPhoto: CoverPhoto_? = null

    @SerializedName("preview_photos")
    @Expose
    var previewPhotos: List<PreviewPhoto>? = null
}