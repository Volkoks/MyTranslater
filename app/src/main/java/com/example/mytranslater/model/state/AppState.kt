package com.example.mytranslater.model.state

import com.example.mytranslater.model.entites.Word

sealed class AppState {
    data class Succes(val data: List<Word>?) : AppState()
    data class Loading(val progress: Int?) : AppState()
    data class Error(val error: Throwable) : AppState()
}