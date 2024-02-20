package dev.amal.core.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, Action, ScreenEvent> : ViewModel() {

    protected val actionChannel = Channel<Action>()
    val actions: Flow<Action> = actionChannel.receiveAsFlow()

    protected val stateFlow = MutableStateFlow(this.defaultState())
    val state: StateFlow<State> = stateFlow.asStateFlow()

    protected abstract fun defaultState(): State

    abstract fun onEvent(event: ScreenEvent)

    protected fun sendAction(action: Action) {
        viewModelScope.launch { actionChannel.send(action) }
    }
}