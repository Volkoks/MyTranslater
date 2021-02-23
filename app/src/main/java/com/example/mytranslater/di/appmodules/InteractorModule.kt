package com.example.mytranslater.di.appmodules

import com.example.mytranslater.model.entites.Word
import com.example.mytranslater.model.repository.Repository
import com.example.mytranslater.model.state.AppState
import com.example.mytranslater.ui.main.MainFaragmentInteractor
import com.example.mytranslater.viewmodel.Interactor
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideInteractor(repo: Repository<List<Word>>): Interactor<AppState> =
        MainFaragmentInteractor(repo)
}