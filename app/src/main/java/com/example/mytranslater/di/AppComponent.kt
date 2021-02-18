package com.example.mytranslater.di


import android.content.Context
import com.example.mytranslater.di.appmodules.*
import com.example.mytranslater.ui.main.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(
    modules = [
        ApiModule::class,
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun app(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(mainFragment: MainFragment)
}