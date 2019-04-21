package bloder.com.data.result.http_result

import bloder.com.data.result.Result

inline class HttpResult<T>(private val result: Result<T>) {

    companion object {
        inline fun <T> success(value : T) : HttpResult<T> = HttpResult(Result.ok(value))
        inline fun <T> requestError(code: Int = -1, errorBody: Exception) : HttpResult<T> =
                HttpResult(HttpFailure<Nothing>(code, errorBody).getFailure())
        suspend inline fun <T> custom(creation: HttpCustomResult<T>.() -> Unit) : HttpResult<T> =
                HttpCustomResult<T>().apply { creation() }.createResult()
    }

    class HttpFailure<T> (private val code: Int, private val errorBody: Exception) {

        fun getFailure() : Result<T> = when(code) {
            400 -> Result.error(HttpCodeException(HttpCode.On400(errorBody)))
            404 -> Result.error(HttpCodeException(HttpCode.On404(errorBody)))
            500 -> Result.error(HttpCodeException(HttpCode.On500(errorBody)))
            403 -> Result.error(HttpCodeException(HttpCode.On403(errorBody)))
            else -> Result.error(HttpCodeException(HttpCode.Unknown))
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun getResponse() : T = when(result.get()) {
        is Result.Failure -> throw (result.get() as Result.Failure).exception as HttpCodeException
        else -> result.get() as T
    }

    fun get(): Result<T> = result
}

fun <T, R> HttpResult<T>.map(transform: (T) -> R) : HttpResult<R> = when(val value = this.get().get()) {
    is Result.Failure -> HttpResult(Result(value))
    else -> HttpResult.success(transform(value as T))
}

fun <T, R> HttpResult<T>.flatMap(transform: (T) -> HttpResult<R>) : HttpResult<R> = when(val value = this.get().get()) {
    is Result.Failure -> HttpResult(Result(value))
    else -> transform(value as T)
}