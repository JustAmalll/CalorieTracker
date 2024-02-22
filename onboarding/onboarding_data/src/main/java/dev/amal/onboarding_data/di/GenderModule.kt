package dev.amal.onboarding_data.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.amal.onboarding_data.gender.repository.GenderRepositoryImpl
import dev.amal.onboarding_data.gender.source.cache.GenderCacheDataSource
import dev.amal.onboarding_data.gender.source.cache.GenderCacheDataSourceImpl
import dev.amal.onboarding_domain.gender.repository.GenderRepository

@Module
@InstallIn(SingletonComponent::class)
object GenderModule {

    @Provides
    fun provideGenderCacheDataSource(preferences: SharedPreferences): GenderCacheDataSource =
        GenderCacheDataSourceImpl(preferences = preferences)

    @Provides
    fun provideGenderRepository(genderCacheDataSource: GenderCacheDataSource): GenderRepository =
        GenderRepositoryImpl(genderCacheDataSource = genderCacheDataSource)
}