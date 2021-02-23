package com.example.mytranslater.model.datasource.retrofit.datasource

import com.example.mytranslater.model.datasource.DataSource
import com.example.mytranslater.model.entites.Word
import io.reactivex.Observable

class LocalDataSource:DataSource<List<Word>> {
    override fun getData(word: String): Observable<List<Word>> {
        TODO("Not yet implemented")//Добавится тело метода когда доработаем Room
    }
}