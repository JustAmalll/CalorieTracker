package dev.amal.onboarding_domain.nutrient_goal.use_case

import dev.amal.core.R
import dev.amal.core.util.UiText
import dev.amal.onboarding_domain.nutrient_goal.use_case.ValidateNutrientsUseCase.ValidateNutrientsResult.Error

class ValidateNutrientsUseCase {

    operator fun invoke(
        carbsRatioText: String,
        proteinRatioText: String,
        fatRatioText: String
    ): ValidateNutrientsResult {
        val carbsRatio = carbsRatioText.toIntOrNull()
        val proteinRatio = proteinRatioText.toIntOrNull()
        val fatRatio = fatRatioText.toIntOrNull()

        if (carbsRatio == null || proteinRatio == null || fatRatio == null) {
            return Error(message = UiText.StringResource(R.string.error_invalid_values))
        }
        if (carbsRatio + proteinRatio + fatRatio != 100) {
            return Error(message = UiText.StringResource(R.string.error_not_100_percent))
        }
        return ValidateNutrientsResult.Success(
            carbsRatio = carbsRatio / 100f,
            proteinRatio = proteinRatio / 100f,
            fatRatio = fatRatio / 100f
        )
    }

    sealed interface ValidateNutrientsResult {
        data class Success(
            val carbsRatio: Float,
            val proteinRatio: Float,
            val fatRatio: Float
        ) : ValidateNutrientsResult

        data class Error(val message: UiText) : ValidateNutrientsResult
    }
}