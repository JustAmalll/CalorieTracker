package dev.amal.onboarding_data.nutrient_goal.source

import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NutrientGoalCacheDataSourceImpl(
    private val preferences: SharedPreferences
) : NutrientGoalCacheDataSource {

    override suspend fun saveNutrientGoal(
        carbsRatio: Float,
        proteinRatio: Float,
        fatRatio: Float
    ): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            preferences.edit()
                .putFloat(KEY_CARB_RATIO, carbsRatio)
                .putFloat(KEY_PROTEIN_RATIO, proteinRatio)
                .putFloat(KEY_FAT_RATIO, fatRatio)
                .apply()
        }
    }

    private companion object {
        private const val KEY_CARB_RATIO = "carb_ratio"
        private const val KEY_PROTEIN_RATIO = "protein_ratio"
        private const val KEY_FAT_RATIO = "fat_ratio"
    }
}