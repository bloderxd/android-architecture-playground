package bloder.com.domain.http_error_case

import bloder.com.data.result.http_result.HttpCodeException
import bloder.com.data.result.http_result.HttpResult
import bloder.com.data.result.http_result.is400

interface HttpErrorModule {

    fun <T> on200(value: HttpResult<T>) {}
    fun on400() {}
    fun on500() {}
}

private fun httpErrorMapper() : Map<Int, () -> Unit> = mapOf(
        200 to {},
        400 to {}
)

inline fun <reified T : Exception> HttpCodeException.handle() = with(this) {
    is400<T> {  }
}