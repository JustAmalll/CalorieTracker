package dev.amal.onboarding_data.activity_and_goal.repository

import dev.amal.core.domain.models.ActivityLevel
import dev.amal.core.domain.models.GoalType
import dev.amal.onboarding_data.activity_and_goal.source.cache.ActivityAndGoalCacheDataSource
import dev.amal.onboarding_domain.activity_and_goal.repository.ActivityAndGoalRepository

class ActivityAndGoalRepositoryImpl(
    private val activityAndGoalCacheDataSource: ActivityAndGoalCacheDataSource
): ActivityAndGoalRepository {

    override suspend fun saveActivityAndGoal(
        activityLevel: ActivityLevel,
        goal: GoalType
    ): Result<Unit> = activityAndGoalCacheDataSource.saveActivityAndGoal(
        activityLevel = activityLevel,
        goal = goal
    )
}