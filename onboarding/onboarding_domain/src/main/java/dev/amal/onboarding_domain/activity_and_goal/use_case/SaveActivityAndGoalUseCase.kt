package dev.amal.onboarding_domain.activity_and_goal.use_case

import dev.amal.core.domain.models.ActivityLevel
import dev.amal.core.domain.models.GoalType
import dev.amal.onboarding_domain.activity_and_goal.repository.ActivityAndGoalRepository

class SaveActivityAndGoalUseCase(
    private val activityAndGoalRepository: ActivityAndGoalRepository
) {

    suspend operator fun invoke(activityLevel: ActivityLevel, goal: GoalType): Result<Unit> =
        activityAndGoalRepository.saveActivityAndGoal(activityLevel = activityLevel, goal = goal)
}