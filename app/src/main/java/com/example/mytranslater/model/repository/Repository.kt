package com.example.mytranslater.model.repository

import io.reactivex.Observable

interface Repository<T> {
    fun getData(word: String,isOnline:Boolean): Observable<T>
}