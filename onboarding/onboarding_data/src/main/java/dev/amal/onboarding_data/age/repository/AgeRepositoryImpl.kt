package dev.amal.onboarding_data.age.repository

import dev.amal.onboarding_data.age.source.cache.AgeCacheDataSource
import dev.amal.onboarding_domain.age.repository.AgeRepository

class AgeRepositoryImpl(
    private val ageCacheDataSource: AgeCacheDataSource
): AgeRepository {

    override suspend fun saveAge(age: Int): Result<Unit> =
        ageCacheDataSource.saveAge(age = age)
}