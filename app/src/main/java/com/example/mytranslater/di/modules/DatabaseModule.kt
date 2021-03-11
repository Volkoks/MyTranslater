package com.example.mytranslater.di.modules

import android.content.Context
import androidx.room.Room
import com.example.mytranslater.application.MyApp
import com.example.mytranslater.di.AppComponent
import com.example.mytranslater.model.room.history_word.HistoryWordDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun hisrotyWordDatabase(context: Context): HistoryWordDatabase {
        return Room.databaseBuilder(
            context,
            HistoryWordDatabase::class.java,
            HistoryWordDatabase.DB_NAME
        ).fallbackToDestructiveMigration().build()
    }
}