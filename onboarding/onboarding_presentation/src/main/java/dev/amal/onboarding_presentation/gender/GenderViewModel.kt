package dev.amal.onboarding_presentation.gender

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.core.domain.models.Gender
import dev.amal.core.util.BaseViewModel
import dev.amal.onboarding_domain.gender.interactor.GenderInteractor
import dev.amal.onboarding_presentation.gender.GenderEvent.OnGenderClick
import dev.amal.onboarding_presentation.gender.GenderEvent.OnNextClicked
import dev.amal.onboarding_presentation.gender.GenderViewModel.GenderAction
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    private val genderInteractor: GenderInteractor
) : BaseViewModel<Gender, GenderAction, GenderEvent>() {

    override fun defaultState() = Gender.MALE

    override fun onEvent(event: GenderEvent) {
        when (event) {
            is OnGenderClick -> stateFlow.update { event.gender }
            OnNextClicked -> saveGenderAndNavigateToTheNextScreen()
        }
    }

    private fun saveGenderAndNavigateToTheNextScreen() {
        viewModelScope.launch {
            genderInteractor.saveGender(gender = state.value).onSuccess {
                actionChannel.send(GenderAction.NavigateToTheNextScreen)
            }
        }
    }

    sealed interface GenderAction {
        data object NavigateToTheNextScreen : GenderAction
    }
}