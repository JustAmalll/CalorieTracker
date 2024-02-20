package dev.amal.onboarding_data.age.source.cache

interface AgeCacheDataSource {
    suspend fun saveAge(age: Int): Result<Unit>
}