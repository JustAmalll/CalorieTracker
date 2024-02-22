package dev.amal.calorietracker.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.amal.core.navigation.Route
import dev.amal.core.util.UiText
import dev.amal.onboarding_presentation.activity_and_goal.ActivityAndGoalAssembly
import dev.amal.onboarding_presentation.age.AgeScreenAssembly
import dev.amal.onboarding_presentation.gender.GenderScreenAssembly
import dev.amal.onboarding_presentation.height_and_weight.HeightAndWeightAssembly
import dev.amal.onboarding_presentation.nutrient_goal.NutrientGoalAssembly
import dev.amal.onboarding_presentation.welcome.WelcomeScreenAssembly

@Composable
fun SetupNavHost(
    navController: NavHostController,
    showSnackBar: suspend (UiText) -> Unit
) {
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
        composable(Route.GENDER) {
            GenderScreenAssembly(navController = navController)
        }
        composable(Route.AGE) {
            AgeScreenAssembly(navController = navController, showSnackBar = showSnackBar)
        }
        composable(Route.HEIGHT_AND_WEIGHT) {
            HeightAndWeightAssembly(navController = navController, showSnackBar = showSnackBar)
        }
        composable(Route.ACTIVITY_AND_GOAL) {
            ActivityAndGoalAssembly(navController = navController)
        }
        composable(Route.NUTRIENT_GOAL) {
            NutrientGoalAssembly(navController = navController, showSnackBar = showSnackBar)
        }
        composable(Route.TRACKER_OVERVIEW) {

        }
        composable(Route.SEARCH) {

        }
    }
}