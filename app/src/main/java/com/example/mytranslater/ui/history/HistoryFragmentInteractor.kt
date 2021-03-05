package com.example.mytranslater.ui.history

import com.example.mytranslater.di.modules.HISTORY_REPO
import com.example.model.entites.Word
import com.example.core.viewmodel.Interactor
import com.example.model.state.AppState
import com.example.repository.repository.Repository
import javax.inject.Inject
import javax.inject.Named

class HistoryFragmentInteractor @Inject constructor(
    @Named(HISTORY_REPO) private val repo: Repository<List<Word>>
) : Interactor<AppState> {


    override suspend fun getData(word: String): AppState {
        return AppState.Succes(repo.getData(word))
    }
}