package bloder.com.concurrency

import bloder.com.concurrency.coroutines.AsyncOptionalContinuation
import bloder.com.concurrency.coroutines.CoroutinesModule
import kotlinx.coroutines.Job

fun coroutines(): CoroutinesModule = object : CoroutinesModule {

    override val job: Job = Job()

    override suspend fun <T> getAsync(block: suspend () -> T): AsyncOptionalContinuation<T> = AsyncOptionalContinuation(block)

    override suspend infix fun <T> AsyncOptionalContinuation<T>.onThrow(block: suspend (Exception) -> T?): T? =  try {
        this.block()
    } catch (e: Exception) {
        block(e)
    }
}