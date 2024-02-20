package dev.amal.onboarding_domain.age.repository

interface AgeRepository {
    suspend fun saveAge(age: Int): Result<Unit>
}