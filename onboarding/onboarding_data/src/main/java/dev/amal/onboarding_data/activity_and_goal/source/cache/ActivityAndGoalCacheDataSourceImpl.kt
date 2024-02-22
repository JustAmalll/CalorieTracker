package dev.amal.onboarding_data.activity_and_goal.source.cache

import android.content.SharedPreferences
import dev.amal.core.domain.models.ActivityLevel
import dev.amal.core.domain.models.GoalType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ActivityAndGoalCacheDataSourceImpl(
    private val preferences: SharedPreferences
): ActivityAndGoalCacheDataSource {

    override suspend fun saveActivityAndGoal(
        activityLevel: ActivityLevel,
        goal: GoalType
    ): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            preferences.edit()
                .putString(KEY_ACTIVITY_LEVEL, activityLevel.name)
                .putString(KEY_GOAL_TYPE, goal.name)
                .apply()
        }
    }

    private companion object {
        private const val KEY_ACTIVITY_LEVEL = "activity_level"
        private const val KEY_GOAL_TYPE = "goal_type"
    }
}