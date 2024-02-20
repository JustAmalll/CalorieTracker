package dev.amal.onboarding_domain.height_and_weight.repository

interface HeightAndWeightRepository {
    suspend fun saveHeightAndWeight(height: Int, weight: Float): Result<Unit>
}