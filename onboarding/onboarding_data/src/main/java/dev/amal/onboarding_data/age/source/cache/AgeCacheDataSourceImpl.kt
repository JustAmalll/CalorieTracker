package dev.amal.onboarding_data.age.source.cache

import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AgeCacheDataSourceImpl(
    private val preferences: SharedPreferences
) : AgeCacheDataSource {

    override suspend fun saveAge(age: Int): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            preferences.edit().putInt(KEY_AGE, age).apply()
        }
    }

    private companion object {
        private const val KEY_AGE = "age"
    }
}