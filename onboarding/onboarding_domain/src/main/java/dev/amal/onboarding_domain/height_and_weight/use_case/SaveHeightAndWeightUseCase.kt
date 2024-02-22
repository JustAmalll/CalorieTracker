package dev.amal.onboarding_domain.height_and_weight.use_case

import dev.amal.onboarding_domain.height_and_weight.repository.HeightAndWeightRepository

class SaveHeightAndWeightUseCase(
    private val heightAndWeightRepository: HeightAndWeightRepository
) {

    suspend operator fun invoke(height: Int, weight: Float): Result<Unit> =
        heightAndWeightRepository.saveHeightAndWeight(height = height, weight = weight)
}