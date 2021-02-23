package com.example.mytranslater.model.datasource

interface DataSource<T> {
    suspend fun getData(word: String): T
}