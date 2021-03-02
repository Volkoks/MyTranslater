package com.example.mytranslater.viewmodel

interface Interactor<T> {
    suspend fun getData(word: String): T
}