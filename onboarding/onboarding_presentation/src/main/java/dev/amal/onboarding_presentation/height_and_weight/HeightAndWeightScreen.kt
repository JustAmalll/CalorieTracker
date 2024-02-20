package dev.amal.onboarding_presentation.height_and_weight

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
import dev.amal.onboarding_presentation.height_and_weight.HeightAndWeightViewModel.HeightAndWeightAction.NavigateToTheNextScreen
import dev.amal.onboarding_presentation.height_and_weight.HeightAndWeightViewModel.HeightAndWeightAction.ShowSnackbar

@Composable
fun HeightAndWeightAssembly(
    navController: NavHostController,
    viewModel: HeightAndWeightViewModel = hiltViewModel(),
    showSnackBar: suspend (UiText) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.actions) { action ->
        when (action) {
            NavigateToTheNextScreen -> navController.navigate(Route.ACTIVITY_AND_GOAL)
            is ShowSnackbar -> showSnackBar(action.message)
        }
    }
    HeightAndWeightScreen(state = state, onEvent = viewModel::onEvent)
}

@Composable
private fun HeightAndWeightScreen(
    state: HeightAndWeightState,
    onEvent: (HeightAndWeightEvent) -> Unit
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
                text = stringResource(id = R.string.whats_your_height),
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            UnitTextField(
                value = state.height,
                onValueChange = { onEvent(HeightAndWeightEvent.OnHeightChanged(it)) },
                unit = stringResource(id = R.string.cm)
            )
            Spacer(modifier = Modifier.height(spacing.spaceLarge))

            Text(
                text = stringResource(id = R.string.whats_your_weight),
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            UnitTextField(
                value = state.weight,
                onValueChange = { onEvent(HeightAndWeightEvent.OnWeightChanged(it)) },
                unit = stringResource(id = R.string.kg)
            )
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = { onEvent(HeightAndWeightEvent.OnNextClicked) },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview
@Composable
fun HeightAndWeightScreenPreview() {
    CalorieTrackerTheme {
        HeightAndWeightScreen(
            state = HeightAndWeightState(),
            onEvent = {}
        )
    }
}