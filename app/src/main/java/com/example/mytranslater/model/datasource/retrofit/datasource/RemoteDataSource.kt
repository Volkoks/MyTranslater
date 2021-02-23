package com.example.mytranslater.model.datasource.retrofit.datasource

import com.example.mytranslater.model.datasource.DataSource
import com.example.mytranslater.model.datasource.retrofit.api.IWordApiService
import com.example.mytranslater.model.entites.Word
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: IWordApiService
) : DataSource<List<Word>> {
    override suspend fun getData(word: String): List<Word> {
        return apiService.search(word).await()
    }


}