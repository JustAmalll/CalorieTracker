package dev.amal.onboarding_data.nutrient_goal.repository

import dev.amal.onboarding_data.nutrient_goal.source.NutrientGoalCacheDataSource
import dev.amal.onboarding_domain.nutrient_goal.repository.NutrientGoalRepository

class NutrientGoalRepositoryImpl(
    private val nutrientGoalCacheDataSource: NutrientGoalCacheDataSource
): NutrientGoalRepository {

    override suspend fun saveNutrientGoal(
        carbsRatio: Float,
        proteinRatio: Float,
        fatRatio: Float
    ): Result<Unit> = nutrientGoalCacheDataSource.saveNutrientGoal(
        carbsRatio = carbsRatio,
        proteinRatio = proteinRatio,
        fatRatio = fatRatio
    )
}