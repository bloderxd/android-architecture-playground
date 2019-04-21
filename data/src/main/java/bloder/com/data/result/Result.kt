package bloder.com.data.result

inline class Result<out T>(private val value: Any?) {

    companion object {
        inline fun <T> ok(value: T) : Result<T> = Result(value)
        inline fun <T> error(exception: Exception) : Result<T> = Result(Failure(exception))
    }

    fun get() : Any? = value

    @Suppress("UNCHECKED_CAST")
    fun getOrThrow() : T = when(value) {
        is Failure -> throw value.exception
        else -> value as T
    }

    open class Failure(val exception: Exception)
}

inline fun <T, R> Result<T>.map(transform: (T) -> R) : Result<R> = when(val value = get()) {
    is Result.Failure -> Result(value)
    else -> Result.ok(transform(value as T))
}

inline fun <T, R> Result<T>.flatMap(transform: (T) -> Result<R>) : Result<R> = when(val value = get()) {
    is Result.Failure -> Result(value)
    else -> transform(value as T)
}