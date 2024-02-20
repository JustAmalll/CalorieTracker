package dev.amal.onboarding_presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import dev.amal.calorietracker.ui.theme.CalorieTrackerTheme
import dev.amal.core.R
import dev.amal.core.navigation.Route
import dev.amal.core_ui.theme.LocalSpacing
import dev.amal.onboarding_presentation.components.ActionButton

@Composable
fun WelcomeScreenAssembly(navController: NavHostController) {
    WelcomeScreen(onNextClicked = { navController.navigate(Route.GENDER) } )
}

@Composable
fun WelcomeScreen(onNextClicked: () -> Unit) {
    val spacing = LocalSpacing.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.welcome_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = onNextClicked,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    CalorieTrackerTheme {
        WelcomeScreen(onNextClicked = {})
    }
}