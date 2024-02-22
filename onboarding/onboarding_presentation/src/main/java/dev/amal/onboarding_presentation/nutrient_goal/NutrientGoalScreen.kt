package dev.amal.onboarding_presentation.nutrient_goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import dev.amal.core.navigation.Route
import dev.amal.core.util.UiText
import dev.amal.core_ui.theme.LocalSpacing
import dev.amal.core_ui.utils.ObserveAsEvents
import dev.amal.onboarding_presentation.components.ActionButton
import dev.amal.onboarding_presentation.components.UnitTextField
import dev.amal.onboarding_presentation.nutrient_goal.NutrientGoalViewModel.NutrientGoalAction.NavigateToTheNextScreen
import dev.amal.onboarding_presentation.nutrient_goal.NutrientGoalViewModel.NutrientGoalAction.ShowSnackbar

@Composable
fun NutrientGoalAssembly(
    navController: NavHostController,
    viewModel: NutrientGoalViewModel = hiltViewModel(),
    showSnackBar: suspend (UiText) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.actions) { action ->
        when(action) {
            NavigateToTheNextScreen -> navController.navigate(Route.TRACKER_OVERVIEW)
            is ShowSnackbar -> showSnackBar(action.message)
        }
    }

    NutrientGoalScreen(state = state, onEvent = viewModel::onEvent)
}

@Composable
private fun NutrientGoalScreen(
    state: NutrientGoalState,
    onEvent: (NutrientGoalEvent) -> Unit
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
                text = stringResource(id = R.string.what_are_your_nutrient_goals),
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = state.carbsRatio,
                onValueChange = { onEvent(NutrientGoalEvent.OnCarbRatioChanged(it)) },
                unit = stringResource(id = R.string.percent_carbs)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = state.proteinRatio,
                onValueChange = { onEvent(NutrientGoalEvent.OnProteinRatioChanged(it)) },
                unit = stringResource(id = R.string.percent_proteins)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = state.fatRatio,
                onValueChange = { onEvent(NutrientGoalEvent.OnFatRatioChanged(it)) },
                unit = stringResource(id = R.string.percent_fats)
            )
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = { onEvent(NutrientGoalEvent.OnNextClicked) },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview
@Composable
fun NutrientGoalScreenPreview() {
    CalorieTrackerTheme {
        NutrientGoalScreen(
            state = NutrientGoalState(),
            onEvent = {}
        )
    }
}