package com.example.mytranslater.model.datasource.retrofit.datasource

import com.example.mytranslater.model.datasource.DataSource
import com.example.mytranslater.model.datasource.retrofit.api.IWordApiService
import com.example.mytranslater.model.entites.Word
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: IWordApiService
) : DataSource<List<Word>> {

    override fun getData(word: String): Observable<List<Word>> {
        return apiService.search(word)
    }

}