package dev.amal.onboarding_data.height_and_weight.source.cache

import android.content.SharedPreferences
import dev.amal.core.domain.preferences.Preferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HeightAndWeightCacheDataSourceImpl(
    private val preferences: SharedPreferences
) : HeightAndWeightCacheDataSource {

    override suspend fun saveHeightAndWeight(
        height: Int,
        weight: Float
    ): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            preferences.edit()
                .putInt(Preferences.KEY_HEIGHT, height)
                .putFloat(Preferences.KEY_WEIGHT, weight)
                .apply()
        }
    }
}