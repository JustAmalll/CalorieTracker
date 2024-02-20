package dev.amal.onboarding_domain.gender.interactor

import dev.amal.core.domain.models.Gender
import dev.amal.onboarding_domain.gender.repository.GenderRepository
import javax.inject.Inject

class GenderInteractor @Inject constructor(
    private val genderRepository: GenderRepository
) {

    suspend fun saveGender(gender: Gender): Result<Unit> =
        genderRepository.saveGender(gender = gender)
}