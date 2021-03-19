package com.example.repository.repository

import com.example.repository.datasource.DataSourceLocal
import com.example.model.entites.Word
import javax.inject.Inject

class HistoryWordRepo @Inject constructor(
    private val localData: DataSourceLocal<List<Word>>
) : Repository<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return localData.getData(word)
    }
}