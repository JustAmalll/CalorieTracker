package dev.amal.onboarding_domain.height_and_weight.interactor

import dev.amal.onboarding_domain.height_and_weight.repository.HeightAndWeightRepository

class HeightAndWeightInteractor(
    private val heightAndWeightRepository: HeightAndWeightRepository
) {

    suspend fun saveHeightAndWeight(height: Int, weight: Float): Result<Unit> =
        heightAndWeightRepository.saveHeightAndWeight(height = height, weight = weight)
}