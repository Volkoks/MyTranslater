package com.example.mytranslater.viewmodel.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytranslater.model.state.AppState
import kotlinx.coroutines.*

abstract class BaseViewModel<T : AppState>(
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData(),
) : ViewModel() {

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            errorReturned(throwable)
        })

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    abstract fun getData(word: String)
    abstract fun errorReturned(t: Throwable)

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

}