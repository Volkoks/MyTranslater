package com.example.mytranslater.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mytranslater.di.annatation.VIewModelKey
import com.example.mytranslater.di.factory.ViewModelFactory
import com.example.mytranslater.ui.main.MainFragmentViewModel
import com.example.mytranslater.ui.screen_word.WordViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @VIewModelKey(MainFragmentViewModel::class)
    protected abstract fun mainViewModel(mainFragmentViewModel: MainFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @VIewModelKey(WordViewModel::class)
    protected abstract fun wordViewModel(wordFragmentViewModel: WordViewModel): ViewModel

}