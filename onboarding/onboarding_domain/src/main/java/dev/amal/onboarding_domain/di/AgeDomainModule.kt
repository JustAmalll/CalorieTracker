package dev.amal.onboarding_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.amal.onboarding_domain.age.repository.AgeRepository
import dev.amal.onboarding_domain.age.use_case.SaveAgeUseCase

@Module
@InstallIn(ViewModelComponent::class)
object AgeDomainModule {

    @ViewModelScoped
    @Provides
    fun provideSaveAgeUseCase(ageRepository: AgeRepository): SaveAgeUseCase =
        SaveAgeUseCase(ageRepository = ageRepository)
}