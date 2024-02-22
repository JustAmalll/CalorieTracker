package dev.amal.onboarding_data.nutrient_goal.source

interface NutrientGoalCacheDataSource {

    suspend fun saveNutrientGoal(
        carbsRatio: Float,
        proteinRatio: Float,
        fatRatio: Float
    ): Result<Unit>
}