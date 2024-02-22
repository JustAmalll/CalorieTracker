package dev.amal.onboarding_data.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.amal.onboarding_data.nutrient_goal.repository.NutrientGoalRepositoryImpl
import dev.amal.onboarding_data.nutrient_goal.source.NutrientGoalCacheDataSource
import dev.amal.onboarding_data.nutrient_goal.source.NutrientGoalCacheDataSourceImpl
import dev.amal.onboarding_domain.nutrient_goal.repository.NutrientGoalRepository

@Module
@InstallIn(SingletonComponent::class)
object NutrientGoalModule {

    @Provides
    fun provideNutrientGoalCacheDataSource(
        preferences: SharedPreferences
    ): NutrientGoalCacheDataSource = NutrientGoalCacheDataSourceImpl(
        preferences = preferences
    )

    @Provides
    fun provideNutrientGoalRepository(
        nutrientGoalCacheDataSource: NutrientGoalCacheDataSource
    ): NutrientGoalRepository = NutrientGoalRepositoryImpl(
        nutrientGoalCacheDataSource = nutrientGoalCacheDataSource
    )
}