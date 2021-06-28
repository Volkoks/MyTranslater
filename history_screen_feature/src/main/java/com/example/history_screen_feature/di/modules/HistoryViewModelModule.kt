package com.example.history_screen_feature.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.history_screen_feature.di.VIEWMODELFACTORY_HISTORY
import com.example.history_screen_feature.di.factory.HistoryViewModelFactory
import com.example.history_screen_feature.history.HistoryViewModel
import com.example.mytranslater.di.annatation.VIewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Named

@Module
abstract class HistoryViewModelModule {

    @Binds
    @Named(VIEWMODELFACTORY_HISTORY)
    internal abstract fun bindHistoryViewModelFactory(factory: HistoryViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @VIewModelKey(HistoryViewModel::class)
    protected abstract fun historyViewModel(wordFragmentViewModel: HistoryViewModel): ViewModel
}