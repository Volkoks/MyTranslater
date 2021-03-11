package com.example.mytranslater.ui.main

import androidx.lifecycle.LiveData
import com.example.mytranslater.di.modules.MAIN_INTERACTOR
import com.example.mytranslater.model.state.AppState
import com.example.mytranslater.viewmodel.Interactor
import com.example.mytranslater.viewmodel.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class MainFragmentViewModel @Inject constructor(
    @Named(MAIN_INTERACTOR)private val interactor: Interactor<AppState>
) : BaseViewModel<AppState>() {

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String) {
        liveDataForViewToObserve.value = AppState.Loading(1)
        cancelJob()
        viewModelCoroutineScope.launch {
            liveDataForViewToObserve.postValue(interactor.getData(word))
        }

    }

    override fun errorReturned(t: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(t))
    }
}