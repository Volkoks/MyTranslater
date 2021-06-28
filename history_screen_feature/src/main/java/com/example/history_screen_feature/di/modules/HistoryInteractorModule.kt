package com.example.history_screen_feature.di.modules

import com.example.core.viewmodel.Interactor
import com.example.history_screen_feature.di.HISTORT_INTERACROR
import com.example.history_screen_feature.history.HistoryFragmentInteractor
import com.example.model.entites.Word
import com.example.model.state.AppState
import com.example.mytranslater.di.modules.HISTORY_REPO
import com.example.repository.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class HistoryInteractorModule {
    @Provides
    @Named(HISTORT_INTERACROR)
    fun provideHistoryInteractor(@Named(HISTORY_REPO) repo: Repository<List<Word>>): Interactor<AppState> =
        HistoryFragmentInteractor(repo)
}