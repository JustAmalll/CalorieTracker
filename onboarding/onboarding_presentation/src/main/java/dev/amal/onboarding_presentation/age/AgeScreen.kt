package dev.amal.onboarding_presentation.age

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.amal.core.R
import dev.amal.core.navigation.Route
import dev.amal.core.util.UiText
import dev.amal.core_ui.theme.LocalSpacing
import dev.amal.core_ui.utils.ObserveAsEvents
import dev.amal.onboarding_presentation.age.AgeViewModel.AgeAction.NavigateToTheNextScreen
import dev.amal.onboarding_presentation.age.AgeViewModel.AgeAction.ShowSnackbar
import dev.amal.onboarding_presentation.components.ActionButton
import dev.amal.onboarding_presentation.components.UnitTextField

@Composable
fun AgeScreenAssembly(
    navController: NavHostController,
    viewModel: AgeViewModel = hiltViewModel(),
    showSnackBar: suspend (UiText) -> Unit
) {
    val age by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.actions) { action ->
        when (action) {
            NavigateToTheNextScreen -> navController.navigate(Route.WEIGHT)
            is ShowSnackbar -> showSnackBar(action.message)
        }
    }
    AgeScreen(age = age, onEvent = viewModel::onEvent)
}

@Composable
fun AgeScreen(
    age: String,
    onEvent: (AgeEvent) -> Unit
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
                text = stringResource(id = R.string.whats_your_age),
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            UnitTextField(
                value = age,
                onValueChange = { onEvent(AgeEvent.OnAgeChanged(it)) },
                unit = stringResource(id = R.string.years)
            )
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = { onEvent(AgeEvent.OnNextClicked) },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}