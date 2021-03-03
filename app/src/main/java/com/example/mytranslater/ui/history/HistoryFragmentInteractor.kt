package com.example.mytranslater.ui.history

import com.example.mytranslater.di.modules.HISTORY_REPO
import com.example.mytranslater.model.entites.Word
import com.example.mytranslater.model.repository.Repository
import com.example.mytranslater.model.state.AppState
import com.example.mytranslater.viewmodel.Interactor
import javax.inject.Inject
import javax.inject.Named

class HistoryFragmentInteractor @Inject constructor(
    @Named(HISTORY_REPO) private val repo: Repository<List<Word>>
) : Interactor<AppState> {


    override suspend fun getData(word: String): AppState {
        return AppState.Succes(repo.getData(word))
    }
}