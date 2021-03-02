package com.example.mytranslater.model.repository


interface Repository<T> {
    suspend fun getData(word: String): T
}