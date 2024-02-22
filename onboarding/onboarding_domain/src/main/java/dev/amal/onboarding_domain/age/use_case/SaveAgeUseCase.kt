package dev.amal.onboarding_domain.age.use_case

import dev.amal.onboarding_domain.age.repository.AgeRepository

class SaveAgeUseCase(
    private val ageRepository: AgeRepository
) {

    suspend operator fun invoke(age: Int): Result<Unit> =
        ageRepository.saveAge(age = age)
}