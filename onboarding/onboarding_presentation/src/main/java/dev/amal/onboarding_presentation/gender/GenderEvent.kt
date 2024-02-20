package dev.amal.onboarding_presentation.gender

import dev.amal.core.domain.models.Gender

sealed interface GenderEvent {
    data class OnGenderClick(val gender: Gender): GenderEvent
    data object OnNextClicked: GenderEvent
}