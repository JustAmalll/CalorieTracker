package dev.amal.onboarding_presentation.age

sealed interface AgeEvent {
    data class OnAgeChanged(val age: String) : AgeEvent
    data object OnNextClicked : AgeEvent
}