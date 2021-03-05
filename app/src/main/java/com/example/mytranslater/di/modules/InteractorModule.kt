package com.example.mytranslater.di.modules

import com.example.model.entites.Word
import com.example.model.state.AppState
import com.example.mytranslater.ui.history.HistoryFragmentInteractor
import com.example.mytranslater.ui.main.MainFaragmentInteractor
import com.example.core.viewmodel.Interactor
import com.example.repository.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    @Named(MAIN_INTERACTOR)
    fun provideMainInteractor(@Named(MAIN_REPO)repo: Repository<List<Word>>): Interactor<AppState> =
        MainFaragmentInteractor(repo)

    @Provides
    @Named(HISTORT_INTERACROR)
    fun provideHistoryInteractor(@Named(HISTORY_REPO)repo: Repository<List<Word>>): Interactor<AppState> =
        HistoryFragmentInteractor(repo)
}