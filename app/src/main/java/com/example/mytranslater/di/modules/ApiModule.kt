package com.example.mytranslater.di.modules

import com.example.mytranslater.utils.AppNetworkStatus
import com.example.repository.datasource.retrofit.api.IWordApiService
import com.example.utils.networkstatus.INetworkStatus
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://dictionary.skyeng.ru/api/public/v1/"

    @Singleton
    @Provides
    fun api(@Named("baseUrl") baseUrl: String): IWordApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient(PODInterceptor()))
            .build()
            .create(IWordApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkStatus(): INetworkStatus = AppNetworkStatus()


    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    private inner class PODInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request())
        }
    }


}