package com.example.mytranslater.ui.main

import com.example.mytranslater.model.entites.Word
import com.example.mytranslater.model.repository.Repository
import com.example.mytranslater.model.state.AppState
import com.example.mytranslater.viewmodel.Interactor
import io.reactivex.Observable
import javax.inject.Inject

class MainFaragmentInteractor @Inject constructor(private val repository: Repository<List<Word>>) :
    Interactor<AppState> {

    override fun getData(word: String): Observable<AppState> {
        return repository.getData(word).map { AppState.Succes(it) }
    }

}
