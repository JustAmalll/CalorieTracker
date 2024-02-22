package dev.amal.onboarding_data.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.amal.onboarding_data.activity_and_goal.repository.ActivityAndGoalRepositoryImpl
import dev.amal.onboarding_data.activity_and_goal.source.cache.ActivityAndGoalCacheDataSource
import dev.amal.onboarding_data.activity_and_goal.source.cache.ActivityAndGoalCacheDataSourceImpl
import dev.amal.onboarding_domain.activity_and_goal.repository.ActivityAndGoalRepository

@Module
@InstallIn(SingletonComponent::class)
object ActivityAndGoalModule {

    @Provides
    fun provideHeightAndWeightCacheDataSource(
        preferences: SharedPreferences
    ): ActivityAndGoalCacheDataSource = ActivityAndGoalCacheDataSourceImpl(
        preferences = preferences
    )

    @Provides
    fun provideActivityAndGoalRepository(
        activityAndGoalCacheDataSource: ActivityAndGoalCacheDataSource
    ): ActivityAndGoalRepository = ActivityAndGoalRepositoryImpl(
        activityAndGoalCacheDataSource = activityAndGoalCacheDataSource
    )
}