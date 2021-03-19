package com.example.mytranslater.di.modules

import com.example.model.entites.Word
import com.example.utils.networkstatus.INetworkStatus
import com.example.repository.datasource.DataSource
import com.example.repository.datasource.DataSourceLocal
import com.example.repository.datasource.retrofit.api.IWordApiService
import com.example.repository.datasource.retrofit.datasource.RemoteDataSource
import com.example.repository.datasource.room.HistoryWordDatabase
import com.example.repository.datasource.room.LocalDataSource
import com.example.repository.repository.HistoryWordRepo
import com.example.repository.repository.RepoWordImpl
import com.example.repository.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class
RepositoryModule {

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