package dev.amal.core.util

sealed interface UiEvent {
    data class Navigate(val route: String): UiEvent
    data object NavigateUp: UiEvent
}