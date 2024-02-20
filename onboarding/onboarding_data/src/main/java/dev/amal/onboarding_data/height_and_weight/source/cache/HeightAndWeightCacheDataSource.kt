package dev.amal.onboarding_data.height_and_weight.source.cache

interface HeightAndWeightCacheDataSource {
    suspend fun saveHeightAndWeight(height: Int, weight: Float): Result<Unit>
}