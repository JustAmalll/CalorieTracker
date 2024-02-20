package dev.amal.onboarding_data.height_and_weight.repository

import dev.amal.onboarding_data.height_and_weight.source.cache.HeightAndWeightCacheDataSource
import dev.amal.onboarding_domain.height_and_weight.repository.HeightAndWeightRepository

class HeightAndWeightRepositoryImpl(
    private val heightAndWeightCacheDataSource: HeightAndWeightCacheDataSource
): HeightAndWeightRepository {

    override suspend fun saveHeightAndWeight(height: Int, weight: Float): Result<Unit> =
        heightAndWeightCacheDataSource.saveHeightAndWeight(height = height, weight = weight)
}