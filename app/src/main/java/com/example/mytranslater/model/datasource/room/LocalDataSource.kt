package com.example.mytranslater.model.datasource.room


import com.example.mytranslater.model.datasource.DataSourceLocal
import com.example.mytranslater.model.entites.Word
import com.example.mytranslater.model.room.history_word.HistoryWordDao
import com.example.mytranslater.model.room.history_word.HistoryWordDatabase
import com.example.mytranslater.utils.convertHistoryWordToWord
import com.example.mytranslater.utils.converterListWordToHistoryWord
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