package com.example.mytranslater.ui.main

import androidx.lifecycle.LiveData
import com.example.mytranslater.model.state.AppState
import com.example.mytranslater.viewmodel.base.BaseViewModel
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val interactor: MainFaragmentInteractor
) : BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String) {
        liveDataForViewToObserve.value = AppState.Loading(1)

        compositeDisposable.add(
            interactor.getData(word)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(getObserver())
        )
    }
    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(appState: AppState) {
                liveDataForViewToObserve.value = appState
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }

            override fun onComplete() {}

        }
    }
}