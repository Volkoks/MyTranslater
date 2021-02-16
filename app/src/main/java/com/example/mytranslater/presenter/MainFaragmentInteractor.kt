package com.example.mytranslater.presenter

import com.example.mytranslater.contract.ContractMainFragment
import com.example.mytranslater.model.entites.Word
import com.example.mytranslater.model.repository.Repository
import com.example.mytranslater.model.state.AppState
import io.reactivex.Observable

class MainFaragmentInteractor(private val repository: Repository<List<Word>>) :
    ContractMainFragment.Interactor<AppState> {

    override fun getData(word: String, isOnline: Boolean): Observable<AppState> {
        return repository.getData(word, isOnline).map { AppState.Succes(it) }
    }

}
