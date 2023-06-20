package com.example.characterViewer.core.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SearchResult(
    @SerializedName("Heading")
    var heading: String,
    @SerializedName("RelatedTopics")
    var relatedTopics: List<RelatedTopic>
)