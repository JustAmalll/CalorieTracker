package dev.amal.onboarding_presentation.height_and_weight

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.core.R
import dev.amal.core.util.BaseViewModel
import dev.amal.core.util.UiText
import dev.amal.onboarding_domain.height_and_weight.use_case.SaveHeightAndWeightUseCase
import dev.amal.onboarding_presentation.height_and_weight.HeightAndWeightEvent.OnHeightChanged
import dev.amal.onboarding_presentation.height_and_weight.HeightAndWeightEvent.OnNextClicked
import dev.amal.onboarding_presentation.height_and_weight.HeightAndWeightEvent.OnWeightChanged
import dev.amal.onboarding_presentation.height_and_weight.HeightAndWeightViewModel.HeightAndWeightAction
import dev.amal.onboarding_presentation.height_and_weight.HeightAndWeightViewModel.HeightAndWeightAction.ShowSnackbar
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightAndWeightViewModel @Inject constructor(
    private val saveHeightAndWeight: SaveHeightAndWeightUseCase
) : BaseViewModel<HeightAndWeightState, HeightAndWeightAction, HeightAndWeightEvent>() {

    override fun defaultState() = HeightAndWeightState()

    override fun onEvent(event: HeightAndWeightEvent) {
        when (event) {
            is OnHeightChanged -> stateFlow.update { it.copy(height = event.value) }
            is OnWeightChanged -> stateFlow.update { it.copy(weight = event.value) }
            OnNextClicked -> saveHeightAndWeightAndNavigateToTheNextScreen()
        }
    }

    private fun saveHeightAndWeightAndNavigateToTheNextScreen() {
        viewModelScope.launch {
            val height = state.value.height.toIntOrNull() ?: run {
                actionChannel.send(
                    ShowSnackbar(UiText.StringResource(R.string.error_height_cant_be_empty))
                )
                return@launch
            }
            val weight= state.value.weight.toFloatOrNull() ?: run {
                actionChannel.send(
                    ShowSnackbar(UiText.StringResource(R.string.error_weight_cant_be_empty))
                )
                return@launch
            }

            saveHeightAndWeight(height = height, weight = weight).onSuccess {
                actionChannel.send(HeightAndWeightAction.NavigateToTheNextScreen)
            }
        }
    }

    sealed interface HeightAndWeightAction {
        data object NavigateToTheNextScreen : HeightAndWeightAction
        data class ShowSnackbar(val message: UiText) : HeightAndWeightAction
    }
}