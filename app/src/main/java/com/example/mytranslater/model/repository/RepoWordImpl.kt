package com.example.mytranslater.model.repository

import com.example.mytranslater.di.appmodules.LOCAL
import com.example.mytranslater.di.appmodules.REMOTE
import com.example.mytranslater.model.datasource.DataSource
import com.example.mytranslater.model.entites.Word
import com.example.mytranslater.model.networkstatus.INetworkStatus
import javax.inject.Inject
import javax.inject.Named


class RepoWordImpl @Inject constructor(
    @Named(REMOTE) private val remoteData: DataSource<List<Word>>,
    @Named(LOCAL) private val localData: DataSource<List<Word>>,
    private val networkStatus: INetworkStatus
) : Repository<List<Word>> {
    override suspend fun getData(word: String): List<Word> {
        return if (networkStatus.isOnline() == true) {
            remoteData.getData(word)
        } else {
            localData.getData(word)
        }
    }
}
