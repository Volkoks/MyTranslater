package com.example.mytranslater.model.room.history_word

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = arrayOf(Index(value = arrayOf("word"), unique = true)))
data class HistoryWord(
    @PrimaryKey
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "translate") val translate: String?,
    @ColumnInfo(name = "id") val id: Int? = null

)