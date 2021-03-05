package com.example.mytranslater.ui.screen_word

import androidx.lifecycle.MutableLiveData
import com.example.core.viewmodel.base.BaseViewModel
import com.example.model.state.AppState
import javax.inject.Inject

class WordViewModel @Inject constructor() : BaseViewModel<AppState>() {

    fun subscribe(): MutableLiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String) {
        TODO("Not yet implemented")
    }

    override fun errorReturned(t: Throwable) {
        TODO("Not yet implemented")
    }


}