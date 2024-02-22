package dev.amal.onboarding_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.amal.onboarding_domain.activity_and_goal.repository.ActivityAndGoalRepository
import dev.amal.onboarding_domain.activity_and_goal.use_case.SaveActivityAndGoalUseCase

@Module
@InstallIn(ViewModelComponent::class)
object ActivityAndGoalDomainModule {

    @ViewModelScoped
    @Provides
    fun provideSaveActivityAndGoalUseCase(
        activityAndGoalRepository: ActivityAndGoalRepository
    ): SaveActivityAndGoalUseCase = SaveActivityAndGoalUseCase(
        activityAndGoalRepository = activityAndGoalRepository
    )
}