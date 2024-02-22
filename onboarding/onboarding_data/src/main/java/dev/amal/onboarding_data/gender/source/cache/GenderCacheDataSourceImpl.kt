package dev.amal.onboarding_data.gender.source.cache

import android.content.SharedPreferences
import dev.amal.core.domain.models.Gender
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GenderCacheDataSourceImpl(
    private val preferences: SharedPreferences
) : GenderCacheDataSource {

    override suspend fun saveGender(gender: Gender): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            preferences.edit().putString(KEY_GENDER, gender.name).apply()
        }
    }

    private companion object {
        private const val KEY_GENDER = "gender"
    }
}