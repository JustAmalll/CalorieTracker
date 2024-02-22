package dev.amal.onboarding_presentation.activity_and_goal

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.amal.core.util.BaseViewModel
import dev.amal.onboarding_domain.activity_and_goal.use_case.SaveActivityAndGoalUseCase
import dev.amal.onboarding_presentation.activity_and_goal.ActivityAndGoalEvent.OnActivityLevelClicked
import dev.amal.onboarding_presentation.activity_and_goal.ActivityAndGoalEvent.OnGoalClicked
import dev.amal.onboarding_presentation.activity_and_goal.ActivityAndGoalEvent.OnNextClicked
import dev.amal.onboarding_presentation.activity_and_goal.ActivityAndGoalViewModel.ActivityAndGoalAction
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityAndGoalViewModel @Inject constructor(
    private val saveActivityAndGoal: SaveActivityAndGoalUseCase
) : BaseViewModel<ActivityAndGoalState, ActivityAndGoalAction, ActivityAndGoalEvent>() {

    override fun defaultState() = ActivityAndGoalState()

    override fun onEvent(event: ActivityAndGoalEvent) {
        when (event) {
            is OnActivityLevelClicked -> stateFlow.update {
                it.copy(selectedActivityLevel = event.activityLevel)
            }

            is OnGoalClicked -> stateFlow.update { it.copy(selectedGoal = event.goal) }
            OnNextClicked -> saveActivityAndGoalAndNavigateToTheNextScreen()
        }
    }

    private fun saveActivityAndGoalAndNavigateToTheNextScreen() {
        viewModelScope.launch {
            saveActivityAndGoal(
                activityLevel = state.value.selectedActivityLevel,
                goal = state.value.selectedGoal
            ).onSuccess {
                actionChannel.send(ActivityAndGoalAction.NavigateToTheNextScreen)
            }
        }
    }

    sealed interface ActivityAndGoalAction {
        data object NavigateToTheNextScreen : ActivityAndGoalAction
    }
}