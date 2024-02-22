package dev.amal.onboarding_data.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.amal.onboarding_data.age.repository.AgeRepositoryImpl
import dev.amal.onboarding_data.age.source.cache.AgeCacheDataSource
import dev.amal.onboarding_data.age.source.cache.AgeCacheDataSourceImpl
import dev.amal.onboarding_domain.age.repository.AgeRepository

@Module
@InstallIn(SingletonComponent::class)
object AgeModule {

    @Provides
    fun provideAgeCacheDataSource(preferences: SharedPreferences): AgeCacheDataSource =
        AgeCacheDataSourceImpl(preferences = preferences)

    @Provides
    fun provideAgeRepository(ageCacheDataSource: AgeCacheDataSource): AgeRepository =
        AgeRepositoryImpl(ageCacheDataSource = ageCacheDataSource)
}