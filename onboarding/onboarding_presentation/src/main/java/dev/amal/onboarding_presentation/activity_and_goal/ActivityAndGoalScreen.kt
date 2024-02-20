package dev.amal.onboarding_presentation.activity_and_goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.amal.calorietracker.ui.theme.CalorieTrackerTheme
import dev.amal.core.R
import dev.amal.core.domain.models.ActivityLevel
import dev.amal.core.domain.models.GoalType
import dev.amal.core.navigation.Route
import dev.amal.core_ui.theme.LocalSpacing
import dev.amal.core_ui.utils.ObserveAsEvents
import dev.amal.onboarding_presentation.activity_and_goal.ActivityAndGoalEvent.OnActivityLevelClicked
import dev.amal.onboarding_presentation.activity_and_goal.ActivityAndGoalEvent.OnGoalClicked
import dev.amal.onboarding_presentation.activity_and_goal.ActivityAndGoalViewModel.ActivityAndGoalAction.NavigateToTheNextScreen
import dev.amal.onboarding_presentation.components.ActionButton

@Composable
fun ActivityAndGoalAssembly(
    navController: NavHostController,
    viewModel: ActivityAndGoalViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.actions) { action ->
        when(action) {
            NavigateToTheNextScreen -> navController.navigate(route = Route.NUTRIENT_GOAL)
        }
    }

    ActivityAndGoalScreen(state = state, onEvent = viewModel::onEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ActivityAndGoalScreen(
    state: ActivityAndGoalState,
    onEvent: (ActivityAndGoalEvent) -> Unit
) {
    val spacing = LocalSpacing.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.whats_your_activity_level),
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            SingleChoiceSegmentedButtonRow {
                ActivityLevel.entries.forEachIndexed { index, activityLevel ->
                    SegmentedButton(
                        selected = activityLevel == state.selectedActivityLevel,
                        onClick = { onEvent(OnActivityLevelClicked(activityLevel)) },
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = ActivityLevel.entries.size
                        )
                    ) {
                        Text(
                            text = stringResource(
                                when (activityLevel) {
                                    ActivityLevel.LOW -> R.string.low
                                    ActivityLevel.MEDIUM -> R.string.medium
                                    ActivityLevel.HIGH -> R.string.high
                                }
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(spacing.spaceLarge))

            Text(
                text = stringResource(id = R.string.lose_keep_or_gain_weight),
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            SingleChoiceSegmentedButtonRow {
                GoalType.entries.forEachIndexed { index, goal ->
                    SegmentedButton(
                        selected = goal == state.selectedGoal,
                        onClick = { onEvent(OnGoalClicked(goal)) },
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = GoalType.entries.size
                        )
                    ) {
                        Text(
                            text = stringResource(
                                when (goal) {
                                    GoalType.LOSE_WEIGHT -> R.string.lose
                                    GoalType.KEEP_WEIGHT -> R.string.keep
                                    GoalType.GAIN_WEIGHT -> R.string.gain
                                }
                            )
                        )
                    }
                }
            }
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = { onEvent(ActivityAndGoalEvent.OnNextClicked) },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview
@Composable
fun ActivityAndGoalScreenPreview() {
    CalorieTrackerTheme {
        ActivityAndGoalScreen(
            state = ActivityAndGoalState(),
            onEvent = {}
        )
    }
}