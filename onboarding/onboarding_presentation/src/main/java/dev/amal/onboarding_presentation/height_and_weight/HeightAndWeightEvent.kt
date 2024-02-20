package dev.amal.onboarding_presentation.height_and_weight

sealed interface HeightAndWeightEvent {
    data class OnHeightChanged(val value: String): HeightAndWeightEvent
    data class OnWeightChanged(val value: String): HeightAndWeightEvent
    data object OnNextClicked: HeightAndWeightEvent
}