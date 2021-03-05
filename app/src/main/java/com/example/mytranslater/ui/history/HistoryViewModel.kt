package com.example.mytranslater.ui.history


import androidx.lifecycle.LiveData
import com.example.mytranslater.di.modules.HISTORT_INTERACROR
import com.example.core.viewmodel.Interactor
import com.example.core.viewmodel.base.BaseViewModel
import com.example.model.state.AppState
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

class HistoryViewModel @Inject constructor(
   @Named(HISTORT_INTERACROR) private val interactor: Interactor<AppState>
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

    override fun onCleared() {
        liveDataForViewToObserve.value = AppState.Succes(null)
        super.onCleared()
    }

}