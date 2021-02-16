package com.example.mytranslater.model.datasource.retrofit.api

import com.example.mytranslater.model.entites.Word
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IWordApiService {

    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<Word>>
}