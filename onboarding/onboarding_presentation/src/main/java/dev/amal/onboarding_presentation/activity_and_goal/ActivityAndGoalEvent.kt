package dev.amal.onboarding_presentation.activity_and_goal

import dev.amal.core.domain.models.ActivityLevel
import dev.amal.core.domain.models.GoalType

sealed interface ActivityAndGoalEvent {
    data class OnActivityLevelClicked(val activityLevel: ActivityLevel): ActivityAndGoalEvent
    data class OnGoalClicked(val goal: GoalType): ActivityAndGoalEvent
    data object OnNextClicked: ActivityAndGoalEvent
}