package dev.amal.onboarding_data.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.amal.onboarding_data.height_and_weight.repository.HeightAndWeightRepositoryImpl
import dev.amal.onboarding_data.height_and_weight.source.cache.HeightAndWeightCacheDataSource
import dev.amal.onboarding_data.height_and_weight.source.cache.HeightAndWeightCacheDataSourceImpl
import dev.amal.onboarding_domain.height_and_weight.interactor.HeightAndWeightInteractor
import dev.amal.onboarding_domain.height_and_weight.repository.HeightAndWeightRepository

@Module
@InstallIn(SingletonComponent::class)
class HeightAndWeightModule {

    @Provides
    fun provideHeightAndWeightCacheDataSource(
        preferences: SharedPreferences
    ): HeightAndWeightCacheDataSource = HeightAndWeightCacheDataSourceImpl(
        preferences = preferences
    )

    @Provides
    fun provideHeightAndWeightRepository(
        heightAndWeightCacheDataSource: HeightAndWeightCacheDataSource
    ): HeightAndWeightRepository = HeightAndWeightRepositoryImpl(
        heightAndWeightCacheDataSource = heightAndWeightCacheDataSource
    )

    @Provides
    fun provideHeightAndWeightInteractor(
        heightAndWeightCacheDataSource: HeightAndWeightRepository
    ): HeightAndWeightInteractor = HeightAndWeightInteractor(
        heightAndWeightRepository = heightAndWeightCacheDataSource
    )
}