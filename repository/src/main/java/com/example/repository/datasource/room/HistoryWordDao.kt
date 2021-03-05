package com.example.repository.datasource.room

import androidx.room.*

@Dao
interface HistoryWordDao {
    @Query("SELECT * FROM HistoryWord")
    suspend fun all(): List<HistoryWord>

    @Query("SELECT * FROM HistoryWord WHERE word LIKE :word")
    suspend fun getDataByWord(word: String): HistoryWord

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: HistoryWord)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<HistoryWord>)

    @Update
    suspend fun update(entity: HistoryWord)

    @Delete
    suspend fun delete(entity: HistoryWord)
}