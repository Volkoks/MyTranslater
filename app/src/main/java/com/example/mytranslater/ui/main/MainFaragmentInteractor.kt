package com.example.mytranslater.ui.main

import com.example.mytranslater.di.modules.MAIN_REPO
import com.example.model.entites.Word
import com.example.core.viewmodel.Interactor
import com.example.model.state.AppState
import com.example.repository.repository.Repository
import javax.inject.Inject
import javax.inject.Named

class MainFaragmentInteractor @Inject constructor(@Named(MAIN_REPO) private val repository: Repository<List<Word>>) :
    Interactor<AppState> {

    override suspend fun getData(word: String): AppState {
        return AppState.Succes(repository.getData(word))
    }

}
