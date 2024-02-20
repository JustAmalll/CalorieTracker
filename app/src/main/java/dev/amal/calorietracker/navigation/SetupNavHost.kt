package dev.amal.calorietracker.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.amal.core.navigation.Route
import dev.amal.onboarding_presentation.gender.GenderScreenAssembly
import dev.amal.onboarding_presentation.welcome.WelcomeScreen
import dev.amal.onboarding_presentation.welcome.WelcomeScreenAssembly

@Composable
fun SetupNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.WELCOME,
        enterTransition = { EnterTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable(Route.WELCOME) {
            WelcomeScreenAssembly(navController = navController)
        }
        composable(Route.AGE) {
            GenderScreenAssembly(navController = navController)
        }
        composable(Route.GENDER) {

        }
        composable(Route.HEIGHT) {

        }
        composable(Route.WEIGHT) {

        }
        composable(Route.NUTRIENT_GOAL) {

        }
        composable(Route.ACTIVITY) {

        }
        composable(Route.GOAL) {

        }
        composable(Route.TRACKER_OVERVIEW) {

        }
        composable(Route.SEARCH) {

        }
    }
}