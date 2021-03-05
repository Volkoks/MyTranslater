package com.example.repository.datasource

import com.example.model.entites.Word

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(listWord: List<Word>)
}