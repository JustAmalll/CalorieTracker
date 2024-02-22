package dev.amal.onboarding_data.height_and_weight.source.cache

import android.content.SharedPreferences
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
                .putInt(KEY_HEIGHT, height)
                .putFloat(KEY_WEIGHT, weight)
                .apply()
        }
    }

    private companion object {
        private const val KEY_WEIGHT = "weight"
        private const val KEY_HEIGHT = "height"
    }
}