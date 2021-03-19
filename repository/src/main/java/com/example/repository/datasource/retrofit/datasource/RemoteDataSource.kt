package com.example.repository.datasource.retrofit.datasource

import com.example.repository.datasource.DataSource
import com.example.repository.datasource.retrofit.api.IWordApiService
import com.example.model.entites.Word
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: IWordApiService
) : DataSource<List<Word>> {
    override suspend fun getData(word: String): List<Word> {
        return apiService.search(word).await()
    }


}