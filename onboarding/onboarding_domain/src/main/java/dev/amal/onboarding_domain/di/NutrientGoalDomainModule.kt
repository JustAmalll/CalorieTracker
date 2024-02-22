package dev.amal.onboarding_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.amal.onboarding_domain.nutrient_goal.repository.NutrientGoalRepository
import dev.amal.onboarding_domain.nutrient_goal.use_case.SaveNutrientGoalUseCase
import dev.amal.onboarding_domain.nutrient_goal.use_case.ValidateNutrientsUseCase

@Module
@InstallIn(ViewModelComponent::class)
object NutrientGoalDomainModule {

    @ViewModelScoped
    @Provides
    fun provideSaveHeightAndWeightUseCase(
        nutrientGoalRepository: NutrientGoalRepository
    ): SaveNutrientGoalUseCase = SaveNutrientGoalUseCase(
        nutrientGoalRepository = nutrientGoalRepository
    )

    @ViewModelScoped
    @Provides
    fun provideValidateNutrientsUseCase(): ValidateNutrientsUseCase =
        ValidateNutrientsUseCase()
}