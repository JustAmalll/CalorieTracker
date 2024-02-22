package dev.amal.onboarding_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.amal.onboarding_domain.height_and_weight.repository.HeightAndWeightRepository
import dev.amal.onboarding_domain.height_and_weight.use_case.SaveHeightAndWeightUseCase

@Module
@InstallIn(ViewModelComponent::class)
object HeightAndWeightDomainModule {

    @ViewModelScoped
    @Provides
    fun provideSaveHeightAndWeightUseCase(
        heightAndWeightRepository: HeightAndWeightRepository
    ): SaveHeightAndWeightUseCase = SaveHeightAndWeightUseCase(
        heightAndWeightRepository = heightAndWeightRepository
    )
}