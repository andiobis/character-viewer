package com.example.characterViewer.core.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Icon(
    @SerializedName("URL")
    var url: String?
)