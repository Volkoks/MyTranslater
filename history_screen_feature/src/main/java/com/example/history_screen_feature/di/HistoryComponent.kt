package com.example.history_screen_feature.di

import android.app.Activity
import com.example.history_screen_feature.di.modules.HistoryInteractorModule
import com.example.history_screen_feature.di.modules.HistoryViewModelModule
import com.example.history_screen_feature.history.HistoryFragment
import com.example.mytranslater.di.AppComponent
import com.example.mytranslater.di.annatation.HistoryScreenScope
import dagger.BindsInstance
import dagger.Component

@HistoryScreenScope
@Component(
    dependencies = [AppComponent::class],
    modules = [
        HistoryViewModelModule::class,
        HistoryInteractorModule::class
    ]
)
interface HistoryComponent {

    fun inject(historyFragment: HistoryFragment)

//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun activity(activity: Activity): Builder
//        fun create(): HistoryComponent
//    }
}