package dev.amal.onboarding_presentation.age

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.core.R
import dev.amal.core.util.BaseViewModel
import dev.amal.core.util.UiText
import dev.amal.onboarding_domain.age.interactor.AgeInteractor
import dev.amal.onboarding_presentation.age.AgeViewModel.AgeAction
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val ageInteractor: AgeInteractor
) : BaseViewModel<String, AgeAction, AgeEvent>() {

    override fun defaultState() = "20"

    override fun onEvent(event: AgeEvent) {
        when (event) {
            is AgeEvent.OnAgeChanged -> if (event.age.length <= 3) {
                stateFlow.update { event.age.filter { it.isDigit() } }
            }

            AgeEvent.OnNextClicked -> saveAgeAndNavigateToTheNextScreen()
        }
    }

    private fun saveAgeAndNavigateToTheNextScreen() {
        viewModelScope.launch {
            val age = state.value.toIntOrNull() ?: run {
                actionChannel.send(
                    AgeAction.ShowSnackbar(UiText.StringResource(R.string.error_age_cant_be_empty))
                )
                return@launch
            }

            ageInteractor.saveAge(age = age).onSuccess {
                actionChannel.send(AgeAction.NavigateToTheNextScreen)
            }
        }
    }

    sealed interface AgeAction {
        data object NavigateToTheNextScreen : AgeAction
        data class ShowSnackbar(val message: UiText) : AgeAction
    }
}