package com.example.mytranslater.viewmodel

import io.reactivex.Observable

interface Interactor<T> {
    fun getData(word: String): Observable<T>
}