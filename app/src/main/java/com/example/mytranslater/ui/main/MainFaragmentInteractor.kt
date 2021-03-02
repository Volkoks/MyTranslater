package com.example.mytranslater.ui.main

import com.example.mytranslater.model.entites.Word
import com.example.mytranslater.model.repository.Repository
import com.example.mytranslater.model.state.AppState
import com.example.mytranslater.viewmodel.Interactor
import javax.inject.Inject

class MainFaragmentInteractor @Inject constructor(private val repository: Repository<List<Word>>) :
    Interactor<AppState> {

    override suspend fun getData(word: String): AppState {
        return AppState.Succes(repository.getData(word))
    }

}
