package dev.amal.onboarding_domain.gender.use_case

import dev.amal.core.domain.models.Gender
import dev.amal.onboarding_domain.gender.repository.GenderRepository

class SaveGenderUseCase(
    private val genderRepository: GenderRepository
) {

    suspend operator fun invoke(gender: Gender): Result<Unit> =
        genderRepository.saveGender(gender = gender)
}