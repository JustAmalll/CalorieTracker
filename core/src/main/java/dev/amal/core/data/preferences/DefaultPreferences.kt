package dev.amal.core.data.preferences

import android.content.SharedPreferences
import dev.amal.core.domain.models.ActivityLevel
import dev.amal.core.domain.models.ActivityLevel.MEDIUM
import dev.amal.core.domain.models.Gender
import dev.amal.core.domain.models.GoalType
import dev.amal.core.domain.models.UserInfo
import dev.amal.core.domain.preferences.Preferences
import dev.amal.core.domain.preferences.Preferences.Companion.KEY_ACTIVITY_LEVEL
import dev.amal.core.domain.preferences.Preferences.Companion.KEY_AGE
import dev.amal.core.domain.preferences.Preferences.Companion.KEY_CARB_RATIO
import dev.amal.core.domain.preferences.Preferences.Companion.KEY_FAT_RATIO
import dev.amal.core.domain.preferences.Preferences.Companion.KEY_GENDER
import dev.amal.core.domain.preferences.Preferences.Companion.KEY_GOAL_TYPE
import dev.amal.core.domain.preferences.Preferences.Companion.KEY_HEIGHT
import dev.amal.core.domain.preferences.Preferences.Companion.KEY_PROTEIN_RATIO
import dev.amal.core.domain.preferences.Preferences.Companion.KEY_WEIGHT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultPreferences(private val preferences: SharedPreferences) : Preferences {

    override suspend fun saveGender(gender: Gender) {
        withContext(Dispatchers.IO) {
            preferences.edit().putString(KEY_GENDER, gender.name).apply()
        }
    }

    override suspend fun saveAge(age: Int) {
        withContext(Dispatchers.IO) {
            preferences.edit().putInt(KEY_AGE, age).apply()
        }
    }

    override suspend fun saveWeight(weight: Float) {
        withContext(Dispatchers.IO) {
            preferences.edit().putFloat(KEY_WEIGHT, weight).apply()
        }
    }

    override suspend fun saveHeight(height: Int) {
        withContext(Dispatchers.IO) {
            preferences.edit().putInt(KEY_HEIGHT, height).apply()
        }
    }

    override suspend fun saveActivityLevel(level: ActivityLevel) {
        withContext(Dispatchers.IO) {
            preferences.edit().putString(KEY_ACTIVITY_LEVEL, level.name).apply()
        }
    }

    override suspend fun saveGoalType(type: GoalType) {
        withContext(Dispatchers.IO) {
            preferences.edit().putString(KEY_GOAL_TYPE, type.name).apply()
        }
    }

    override suspend fun saveCarbRatio(ratio: Float) {
        withContext(Dispatchers.IO) {
            preferences.edit().putFloat(KEY_CARB_RATIO, ratio).apply()
        }
    }

    override suspend fun saveProteinRatio(ratio: Float) {
        withContext(Dispatchers.IO) {
            preferences.edit().putFloat(KEY_PROTEIN_RATIO, ratio).apply()
        }
    }

    override suspend fun saveFatRatio(ratio: Float) {
        withContext(Dispatchers.IO) {
            preferences.edit().putFloat(KEY_FAT_RATIO, ratio).apply()
        }
    }

    override suspend fun loadUserInfo(): UserInfo = withContext(Dispatchers.IO) {
        val age = preferences.getInt(KEY_AGE, -1)
        val height = preferences.getInt(KEY_HEIGHT, -1)
        val weight = preferences.getFloat(KEY_WEIGHT, -1f)
        val genderString = preferences.getString(KEY_GENDER, null)
        val activityLevelString = preferences.getString(KEY_ACTIVITY_LEVEL, null)
        val goalType = preferences.getString(KEY_GOAL_TYPE, null)
        val carbRatio = preferences.getFloat(KEY_CARB_RATIO, -1f)
        val proteinRatio = preferences.getFloat(KEY_PROTEIN_RATIO, -1f)
        val fatRatio = preferences.getFloat(KEY_FAT_RATIO, -1f)

        UserInfo(
            gender = Gender.valueOf(genderString ?: Gender.MALE.name),
            age = age,
            weight = weight,
            height = height,
            activityLevel = ActivityLevel.valueOf(activityLevelString ?: MEDIUM.name),
            goalType = GoalType.valueOf(goalType ?: GoalType.KEEP_WEIGHT.name),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio
        )
    }
}