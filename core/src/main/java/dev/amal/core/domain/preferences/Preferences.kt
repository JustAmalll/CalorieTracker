package dev.amal.core.domain.preferences

import dev.amal.core.domain.models.ActivityLevel
import dev.amal.core.domain.models.Gender
import dev.amal.core.domain.models.GoalType
import dev.amal.core.domain.models.UserInfo

interface Preferences {
    suspend fun saveGender(gender: Gender)
    suspend fun saveAge(age: Int)
    suspend fun saveWeight(weight: Float)
    suspend fun saveHeight(height: Int)
    suspend fun saveActivityLevel(level: ActivityLevel)
    suspend fun saveGoalType(type: GoalType)
    suspend fun saveCarbRatio(ratio: Float)
    suspend fun saveProteinRatio(ratio: Float)
    suspend fun saveFatRatio(ratio: Float)

    suspend fun loadUserInfo(): UserInfo

    companion object {
        const val KEY_GENDER = "gender"
        const val KEY_AGE = "age"
        const val KEY_WEIGHT = "weight"
        const val KEY_HEIGHT = "height"
        const val KEY_ACTIVITY_LEVEL = "activity_level"
        const val KEY_GOAL_TYPE = "goal_type"
        const val KEY_CARB_RATIO = "carb_ratio"
        const val KEY_PROTEIN_RATIO = "protein_ratio"
        const val KEY_FAT_RATIO = "fat_ratio"
    }
}