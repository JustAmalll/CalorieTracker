package dev.amal.core.domain.use_case

class FilterOutDigitsUseCase {

    operator fun invoke(text: String): String =
        text.filter { it.isDigit() }
}