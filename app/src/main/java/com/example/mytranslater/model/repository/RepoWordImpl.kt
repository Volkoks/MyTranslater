package com.example.mytranslater.model.repository

import com.example.mytranslater.model.datasource.DataSource
import com.example.mytranslater.model.entites.Word
import io.reactivex.Observable


class RepoWordImpl(
    private val remoteData: DataSource<List<Word>>,
    private val localData: DataSource<List<Word>>
) : Repository<List<Word>> {
    override fun getData(word: String, isOnline: Boolean): Observable<List<Word>> {
        return if (isOnline) {
            remoteData.getData(word)
        } else {
            localData.getData(word)
        }
    }
}
