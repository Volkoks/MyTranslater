package com.example.mytranslater.model.repository

import com.example.mytranslater.di.appmodules.LOCAL
import com.example.mytranslater.di.appmodules.REMOTE
import com.example.mytranslater.model.datasource.DataSource
import com.example.mytranslater.model.entites.Word
import com.example.mytranslater.model.networkstatus.INetworkStatus
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named


class RepoWordImpl @Inject constructor(
    @Named(REMOTE) private val remoteData: DataSource<List<Word>>,
    @Named(LOCAL) private val localData: DataSource<List<Word>>,
    private val networkStatus: INetworkStatus
) : Repository<List<Word>> {
    override fun getData(word: String): Observable<List<Word>> {
        return networkStatus.isOnline().flatMap { isOnline ->
            if (isOnline) {
                remoteData.getData(word)
            } else {
                localData.getData(word)
            }
        }
    }
}
