package bloder.com.presentation.render

import bloder.com.presentation.state.State
import kotlinx.coroutines.channels.ConflatedBroadcastChannel

open class ViewRenderer {

    private val renders: MutableMap<State, ConflatedBroadcastChannel<State>> = mutableMapOf()

    suspend fun <T : State> render(state: T) = renders[state]?.send(state) ?: suspend {
        renders[state] = ConflatedBroadcastChannel()
        renders[state]?.send(state)
    }()
}