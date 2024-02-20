package dev.amal.onboarding_domain.activity_and_goal.interactor

import dev.amal.core.domain.models.ActivityLevel
import dev.amal.core.domain.models.GoalType
import dev.amal.onboarding_domain.activity_and_goal.repository.ActivityAndGoalRepository

class ActivityAndGoalInteractor(
    private val activityAndGoalRepository: ActivityAndGoalRepository
) {

    suspend fun saveActivityAndGoal(activityLevel: ActivityLevel, goal: GoalType): Result<Unit> =
        activityAndGoalRepository.saveActivityAndGoal(activityLevel = activityLevel, goal = goal)
}