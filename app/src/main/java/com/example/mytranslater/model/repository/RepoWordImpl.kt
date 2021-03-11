package com.example.mytranslater.model.repository

import com.example.mytranslater.model.datasource.DataSource
import com.example.mytranslater.model.datasource.DataSourceLocal
import com.example.mytranslater.model.entites.Word
import com.example.mytranslater.model.networkstatus.INetworkStatus
import javax.inject.Inject


class RepoWordImpl @Inject constructor(
    private val remoteData: DataSource<List<Word>>,
    private val localData: DataSourceLocal<List<Word>>,
    private val networkStatus: INetworkStatus
) : Repository<List<Word>> {
    override suspend fun getData(word: String): List<Word> {
        return if (networkStatus.isOnline() == true) {
            val listWord = remoteData.getData(word)
            localData.saveToDB(listWord)
            listWord
        } else {
            localData.getData(word)
        }
    }
}
