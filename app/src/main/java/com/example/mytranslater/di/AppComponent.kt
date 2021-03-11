package com.example.mytranslater.di



import android.content.Context
import androidx.fragment.app.Fragment
import com.example.mytranslater.di.modules.*
import com.example.mytranslater.ui.main.MainFragment
import com.example.mytranslater.ui.screen_word.WordFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(
    modules = [
        ApiModule::class,
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        DatabaseModule::class
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
    fun inject(wordFragment: WordFragment)

}

fun Fragment.appComponent(): AppComponent =
    (context as? IAppComponentProvider)?.provideAppComponent()
        ?: throw IllegalStateException("AppComponent not implemented: $context")