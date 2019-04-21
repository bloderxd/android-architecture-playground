package bloder.com.data.result.http_result

sealed class HttpCode {
    open class On400<out T : Exception>(val errorBody: T) : HttpCode()
    open class On404<out T : Exception>(val errorBody: T) : HttpCode()
    open class On500<out T : Exception>(val errorBody: T) : HttpCode()
    open class On403<out T : Exception>(val errorBody: T) : HttpCode()
    object Unknown : HttpCode()
}

class HttpCodeException(val code: HttpCode) : Exception()

inline fun <reified T : Exception> HttpCodeException.is400(action: (T?) -> Unit) {
    if (this.code is HttpCode.On400<*> && (this.code.errorBody::class.java) == T::class.java) {
        action((this.code as HttpCode.On400<T>).errorBody)
    }
}

inline fun <reified T : Exception> HttpCodeException.is404(action: (T?) -> Unit) {
    if (this.code is HttpCode.On404<*> && (this.code.errorBody::class.java) == T::class.java) {
        action((this.code as HttpCode.On404<T>).errorBody)
    }
}

inline fun <reified T : Exception> HttpCodeException.is500(action: (T?) -> Unit) {
    if (this.code is HttpCode.On500<*> && (this.code.errorBody::class.java) == T::class.java) {
        action((this.code as HttpCode.On500<T>).errorBody)
    }
}

inline fun <reified T : Exception> HttpCodeException.is403(action: (T?) -> Unit) {
    if (this.code is HttpCode.On403<*> && (this.code.errorBody::class.java) == T::class.java) {
        action((this.code as HttpCode.On403<T>).errorBody)
    }
}