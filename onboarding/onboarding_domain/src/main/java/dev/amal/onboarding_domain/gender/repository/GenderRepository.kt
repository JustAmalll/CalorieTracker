package dev.amal.onboarding_domain.gender.repository

import dev.amal.core.domain.models.Gender

interface GenderRepository {
    suspend fun saveGender(gender: Gender): Result<Unit>
}