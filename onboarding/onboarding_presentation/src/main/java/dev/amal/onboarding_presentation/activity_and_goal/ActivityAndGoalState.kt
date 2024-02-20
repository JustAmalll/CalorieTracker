package dev.amal.onboarding_presentation.activity_and_goal

import androidx.compose.runtime.Immutable
import dev.amal.core.domain.models.ActivityLevel
import dev.amal.core.domain.models.GoalType

@Immutable
data class ActivityAndGoalState(
    val selectedGoal: GoalType = GoalType.KEEP_WEIGHT,
    val selectedActivityLevel: ActivityLevel = ActivityLevel.MEDIUM
)