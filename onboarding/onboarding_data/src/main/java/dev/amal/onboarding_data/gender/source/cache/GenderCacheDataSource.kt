package dev.amal.onboarding_data.gender.source.cache

import dev.amal.core.domain.models.Gender

interface GenderCacheDataSource {
    suspend fun saveGender(gender: Gender): Result<Unit>
}