package com.example.mytranslater.ui.main

import androidx.lifecycle.LiveData
import com.example.mytranslater.di.modules.MAIN_INTERACTOR
import com.example.core.viewmodel.Interactor
import com.example.core.viewmodel.base.BaseViewModel
import com.example.model.state.AppState
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