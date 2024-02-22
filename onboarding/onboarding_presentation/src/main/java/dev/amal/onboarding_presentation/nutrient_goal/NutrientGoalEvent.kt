package dev.amal.onboarding_presentation.nutrient_goal

sealed interface NutrientGoalEvent {
    data class OnCarbRatioChanged(val value: String) : NutrientGoalEvent
    data class OnProteinRatioChanged(val value: String) : NutrientGoalEvent
    data class OnFatRatioChanged(val value: String) : NutrientGoalEvent
    data object OnNextClicked : NutrientGoalEvent
}