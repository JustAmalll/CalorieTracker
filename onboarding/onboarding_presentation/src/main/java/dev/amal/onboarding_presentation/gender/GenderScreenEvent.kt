package dev.amal.onboarding_presentation.gender

import dev.amal.core.domain.models.Gender

sealed interface GenderScreenEvent {
    data class OnGenderClick(val gender: Gender): GenderScreenEvent
    data object OnNextClicked: GenderScreenEvent
}