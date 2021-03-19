package com.example.model.entites

import com.google.gson.annotations.SerializedName

data class Word(
    @field:SerializedName("id")val id:Int?,
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meanings>?
)
