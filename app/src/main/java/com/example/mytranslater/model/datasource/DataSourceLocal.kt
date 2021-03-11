package com.example.mytranslater.model.datasource

import com.example.mytranslater.model.entites.Word


interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(listWord: List<Word>)
}