package com.example.mytranslater.di.appmodules

import com.example.mytranslater.model.datasource.DataSource
import com.example.mytranslater.model.datasource.retrofit.api.IWordApiService
import com.example.mytranslater.model.datasource.retrofit.datasource.LocalDataSource
import com.example.mytranslater.model.datasource.retrofit.datasource.RemoteDataSource
import com.example.mytranslater.model.entites.Word
import com.example.mytranslater.model.networkstatus.INetworkStatus
import com.example.mytranslater.model.repository.RepoWordImpl
import com.example.mytranslater.model.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(REMOTE)
    fun provideDataSourceRemote(api:IWordApiService): DataSource<List<Word>> = RemoteDataSource(api)

    @Provides
    @Singleton
    @Named(LOCAL)
    fun provideDataSourceLocal(): DataSource<List<Word>> = LocalDataSource()

    @Provides
    @Singleton
    fun provideRepo(
        @Named(REMOTE) remoteDataSource: DataSource<List<Word>>,
        @Named(LOCAL) localDataSource: DataSource<List<Word>>,
        networkStatus:INetworkStatus
    ): Repository<List<Word>> = RepoWordImpl(remoteDataSource, localDataSource,networkStatus)
}