package com.example.mytranslater.contract

import com.example.mytranslater.model.state.AppState
import io.reactivex.Observable

interface ContractMainFragment {
    interface View {
        fun renderData(appState: AppState)
    }

    interface Presenter <T : AppState, V :View> {
        fun attachView(view: V)
        fun detachView(view: V)
        fun getData(word: String, isOnline: Boolean)
    }
    interface Interactor<T> {
        fun getData(word: String, isOnline: Boolean): Observable<T>
    }
}