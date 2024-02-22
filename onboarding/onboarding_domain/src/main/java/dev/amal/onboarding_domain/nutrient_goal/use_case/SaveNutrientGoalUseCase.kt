package dev.amal.onboarding_domain.nutrient_goal.use_case

import dev.amal.onboarding_domain.nutrient_goal.repository.NutrientGoalRepository

class SaveNutrientGoalUseCase(
    private val nutrientGoalRepository: NutrientGoalRepository
) {

    suspend operator fun invoke(
        carbsRatio: Float,
        proteinRatio: Float,
        fatRatio: Float
    ): Result<Unit> = nutrientGoalRepository.saveNutrientGoal(
        carbsRatio = carbsRatio,
        proteinRatio = proteinRatio,
        fatRatio = fatRatio
    )
}