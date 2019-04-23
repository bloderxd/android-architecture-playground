package bloder.com.concurrency.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

interface CoroutinesModule : CoroutineScope {
    val job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    suspend fun <T> getOnBackground(block: suspend () -> T) : AsyncOptionalContinuation<T>

    suspend infix fun <T> AsyncOptionalContinuation<T>.onThrow(block: suspend (Exception) -> T?) : T?
}

inline class AsyncOptionalContinuation<T>(val block: suspend () -> T?)

