package com.example.characterViewer.core.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class RelatedTopic(
    @SerializedName("FirstURL")
    var firstURL: String,
    @SerializedName("Icon")
    var icon: Icon,
    @SerializedName("Result")
    var result: String,
    @SerializedName("Text")
    var text: String
)