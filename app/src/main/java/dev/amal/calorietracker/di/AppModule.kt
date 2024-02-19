package dev.amal.calorietracker.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.amal.core.data.preferences.DefaultPreferences
import dev.amal.core.domain.preferences.Preferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences =
        app.getSharedPreferences("shared_pref", MODE_PRIVATE)

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences): Preferences =
         DefaultPreferences(preferences = sharedPreferences)
}