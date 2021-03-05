package com.example.repository.datasource.room


import com.example.model.entites.Word
import com.example.repository.datasource.DataSourceLocal
import com.example.repository.datasource.convertHistoryWordToWord
import com.example.repository.datasource.converterListWordToHistoryWord
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val db: HistoryWordDatabase
) : DataSourceLocal<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return convertHistoryWordToWord(db.getHistoryWordDao().all())
    }

    override suspend fun saveToDB(listWord: List<Word>) {
        converterListWordToHistoryWord(listWord)?.let {
            db.getHistoryWordDao().insert(it)
        }
    }
}