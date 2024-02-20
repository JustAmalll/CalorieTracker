package dev.amal.onboarding_presentation.gender

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.amal.core.R
import dev.amal.core.domain.models.Gender
import dev.amal.core.navigation.Route
import dev.amal.core_ui.theme.LocalSpacing
import dev.amal.core_ui.utils.ObserveAsEvents
import dev.amal.onboarding_presentation.components.ActionButton
import dev.amal.onboarding_presentation.gender.GenderEvent.OnGenderClick
import dev.amal.onboarding_presentation.gender.GenderEvent.OnNextClicked
import dev.amal.onboarding_presentation.gender.GenderViewModel.GenderAction.NavigateToTheNextScreen

@Composable
fun GenderScreenAssembly(
    viewModel: GenderViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val selectedGender by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(flow = viewModel.actions) { action ->
        when (action) {
            NavigateToTheNextScreen -> navController.navigate(route = Route.AGE)
        }
    }

    GenderScreen(
        selectedGender = selectedGender,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GenderScreen(
    selectedGender: Gender,
    onEvent: (GenderEvent) -> Unit
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
                text = stringResource(id = R.string.whats_your_gender),
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            SingleChoiceSegmentedButtonRow {
                Gender.entries.forEachIndexed { index, gender ->
                    SegmentedButton(
                        selected = gender == selectedGender,
                        onClick = { onEvent(OnGenderClick(gender = gender)) },
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = Gender.entries.size
                        )
                    ) {
                        Text(
                            text = stringResource(
                                when (gender) {
                                    Gender.MALE -> R.string.male
                                    Gender.FEMALE -> R.string.female
                                }
                            )
                        )
                    }
                }
            }
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = { onEvent(OnNextClicked) },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}