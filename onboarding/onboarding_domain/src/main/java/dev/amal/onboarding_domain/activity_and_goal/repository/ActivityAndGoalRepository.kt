package dev.amal.onboarding_domain.activity_and_goal.repository

import dev.amal.core.domain.models.ActivityLevel
import dev.amal.core.domain.models.GoalType

interface ActivityAndGoalRepository {
    suspend fun saveActivityAndGoal(activityLevel: ActivityLevel, goal: GoalType): Result<Unit>
}