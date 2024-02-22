package dev.amal.onboarding_domain.nutrient_goal.repository

interface NutrientGoalRepository {

    suspend fun saveNutrientGoal(
        carbsRatio: Float,
        proteinRatio: Float,
        fatRatio: Float
    ): Result<Unit>
}