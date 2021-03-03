package com.example.mytranslater.di.modules

import com.example.mytranslater.model.datasource.DataSource
import com.example.mytranslater.model.datasource.DataSourceLocal
import com.example.mytranslater.model.datasource.retrofit.api.IWordApiService
import com.example.mytranslater.model.datasource.room.LocalDataSource
import com.example.mytranslater.model.datasource.retrofit.datasource.RemoteDataSource
import com.example.mytranslater.model.entites.Word
import com.example.mytranslater.model.networkstatus.INetworkStatus
import com.example.mytranslater.model.repository.HistoryWordRepo
import com.example.mytranslater.model.repository.RepoWordImpl
import com.example.mytranslater.model.repository.Repository
import com.example.mytranslater.model.room.history_word.HistoryWordDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideDataSourceRemote(api: IWordApiService): DataSource<List<Word>> =
        RemoteDataSource(api)

    @Provides
    @Singleton
    fun provideDataSourceLocal(database: HistoryWordDatabase): DataSourceLocal<List<Word>> =
        LocalDataSource(database)

    @Provides
    @Singleton
    @Named(MAIN_REPO)
    fun provideMainRepo(
        remoteDataSource: DataSource<List<Word>>,
        localDataSource: DataSourceLocal<List<Word>>,
        networkStatus: INetworkStatus
    ): Repository<List<Word>> = RepoWordImpl(remoteDataSource, localDataSource, networkStatus)

    @Provides
    @Singleton
    @Named(HISTORY_REPO)
    fun provideHistoryRepo(localDataSource: DataSourceLocal<List<Word>>): Repository<List<Word>> =
        HistoryWordRepo(localDataSource)

}