package dev.amal.onboarding_data.activity_and_goal.source.cache

import dev.amal.core.domain.models.ActivityLevel
import dev.amal.core.domain.models.GoalType

interface ActivityAndGoalCacheDataSource {
    suspend fun saveActivityAndGoal(activityLevel: ActivityLevel, goal: GoalType): Result<Unit>
}