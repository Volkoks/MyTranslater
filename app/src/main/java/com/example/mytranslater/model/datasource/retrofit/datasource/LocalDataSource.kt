package com.example.mytranslater.model.datasource.retrofit.datasource

import com.example.mytranslater.model.datasource.DataSource
import com.example.mytranslater.model.entites.Word

class LocalDataSource:DataSource<List<Word>> {
    override suspend fun getData(word: String): List<Word> {
        TODO("Not yet implemented")//Добавится тело метода когда доработаем Room
    }
}