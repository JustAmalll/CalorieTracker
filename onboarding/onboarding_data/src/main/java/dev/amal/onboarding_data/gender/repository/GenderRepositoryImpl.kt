package dev.amal.onboarding_data.gender.repository

import dev.amal.core.domain.models.Gender
import dev.amal.onboarding_data.gender.source.cache.GenderCacheDataSource
import dev.amal.onboarding_domain.gender.repository.GenderRepository

class GenderRepositoryImpl(
    private val genderCacheDataSource: GenderCacheDataSource
): GenderRepository {

    override suspend fun saveGender(gender: Gender): Result<Unit> =
        genderCacheDataSource.saveGender(gender = gender)
}