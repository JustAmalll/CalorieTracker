package dev.amal.onboarding_presentation.nutrient_goal

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.core.domain.use_case.FilterOutDigitsUseCase
import dev.amal.core.util.BaseViewModel
import dev.amal.core.util.UiText
import dev.amal.onboarding_domain.nutrient_goal.use_case.SaveNutrientGoalUseCase
import dev.amal.onboarding_domain.nutrient_goal.use_case.ValidateNutrientsUseCase
import dev.amal.onboarding_domain.nutrient_goal.use_case.ValidateNutrientsUseCase.ValidateNutrientsResult.Error
import dev.amal.onboarding_domain.nutrient_goal.use_case.ValidateNutrientsUseCase.ValidateNutrientsResult.Success
import dev.amal.onboarding_presentation.nutrient_goal.NutrientGoalEvent.OnCarbRatioChanged
import dev.amal.onboarding_presentation.nutrient_goal.NutrientGoalEvent.OnFatRatioChanged
import dev.amal.onboarding_presentation.nutrient_goal.NutrientGoalEvent.OnProteinRatioChanged
import dev.amal.onboarding_presentation.nutrient_goal.NutrientGoalViewModel.NutrientGoalAction
import dev.amal.onboarding_presentation.nutrient_goal.NutrientGoalViewModel.NutrientGoalAction.NavigateToTheNextScreen
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val saveNutrientGoal: SaveNutrientGoalUseCase,
    private val filterOutDigits: FilterOutDigitsUseCase,
    private val validateNutrients: ValidateNutrientsUseCase,
) : BaseViewModel<NutrientGoalState, NutrientGoalAction, NutrientGoalEvent>() {

    override fun defaultState() = NutrientGoalState()

    override fun onEvent(event: NutrientGoalEvent) {
        when (event) {
            is OnCarbRatioChanged -> stateFlow.update {
                it.copy(carbsRatio = filterOutDigits(event.value))
            }

            is OnProteinRatioChanged -> stateFlow.update {
                it.copy(proteinRatio = filterOutDigits(event.value))
            }

            is OnFatRatioChanged -> stateFlow.update {
                it.copy(fatRatio = filterOutDigits(event.value))
            }

            is NutrientGoalEvent.OnNextClicked -> {
                val result = validateNutrients(
                    carbsRatioText = state.value.carbsRatio,
                    proteinRatioText = state.value.proteinRatio,
                    fatRatioText = state.value.fatRatio
                )
                when (result) {
                    is Success -> viewModelScope.launch {
                        saveNutrientGoal(
                            carbsRatio = result.carbsRatio,
                            proteinRatio = result.proteinRatio,
                            fatRatio = result.fatRatio
                        ).onSuccess {
                            actionChannel.send(NavigateToTheNextScreen)
                        }
                    }

                    is Error -> sendAction(NutrientGoalAction.ShowSnackbar(result.message))
                }
            }
        }
    }

    sealed interface NutrientGoalAction {
        data object NavigateToTheNextScreen : NutrientGoalAction
        data class ShowSnackbar(val message: UiText) : NutrientGoalAction
    }
}