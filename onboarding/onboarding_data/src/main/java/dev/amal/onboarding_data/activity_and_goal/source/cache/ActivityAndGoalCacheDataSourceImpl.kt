package dev.amal.onboarding_data.activity_and_goal.source.cache

import android.content.SharedPreferences
import dev.amal.core.domain.models.ActivityLevel
import dev.amal.core.domain.models.GoalType
import dev.amal.core.domain.preferences.Preferences
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
                .putString(Preferences.KEY_ACTIVITY_LEVEL, activityLevel.name)
                .putString(Preferences.KEY_GOAL_TYPE, goal.name)
                .apply()
        }
    }
}