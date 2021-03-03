package com.example.mytranslater.model.room.history_word

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(HistoryWord::class), version = 1, exportSchema = false)
abstract class HistoryWordDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "history_word_db"
    }
    abstract fun getHistoryWordDao(): HistoryWordDao
}