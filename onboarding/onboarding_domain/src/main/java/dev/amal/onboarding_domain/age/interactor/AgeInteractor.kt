package dev.amal.onboarding_domain.age.interactor

import dev.amal.onboarding_domain.age.repository.AgeRepository

class AgeInteractor(private val ageRepository: AgeRepository) {

    suspend fun saveAge(age: Int): Result<Unit> =
        ageRepository.saveAge(age = age)
}