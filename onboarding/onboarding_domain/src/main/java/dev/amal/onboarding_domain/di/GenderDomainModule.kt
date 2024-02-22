package dev.amal.onboarding_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.amal.onboarding_domain.gender.repository.GenderRepository
import dev.amal.onboarding_domain.gender.use_case.SaveGenderUseCase

@Module
@InstallIn(ViewModelComponent::class)
object GenderDomainModule {

    @ViewModelScoped
    @Provides
    fun provideSaveGenderUseCase(genderRepository: GenderRepository): SaveGenderUseCase =
        SaveGenderUseCase(genderRepository = genderRepository)
}