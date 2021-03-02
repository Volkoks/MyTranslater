package com.example.mytranslater.ui.screen_word

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytranslater.model.entites.Word
import com.example.mytranslater.model.state.AppState
import com.example.mytranslater.viewmodel.base.BaseViewModel
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