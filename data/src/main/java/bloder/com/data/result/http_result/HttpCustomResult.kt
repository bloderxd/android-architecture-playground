package bloder.com.data.result.http_result

class HttpCustomResult<T> {

    private lateinit var loadFromDbAction: suspend () -> T
    private lateinit var loadFromRequestAction: suspend () -> HttpResult<T>
    private lateinit var cacheResponseAction: suspend (T) -> Unit
    private lateinit var shouldLoadFromRequestChecking: suspend (T) -> Boolean

    fun loadFromDb(action: suspend () -> T) : HttpCustomResult<T> {
        loadFromDbAction = action
        return this
    }

    fun loadFromRequest(action: suspend () -> HttpResult<T>) : HttpCustomResult<T> {
        loadFromRequestAction = action
        return this
    }

    fun shouldLoadFromRequest(checking: suspend (T) -> Boolean) : HttpCustomResult<T> {
        shouldLoadFromRequestChecking = checking
        return this
    }

    fun cacheResponse(action: suspend (T) -> Unit) : HttpCustomResult<T> {
        cacheResponseAction = action
        return this
    }

    suspend fun createResult() : HttpResult<T> {
        val dataFromDb = loadFromDbAction()
        return if (shouldLoadFromRequestChecking(dataFromDb)) {
            loadFromRequestAction()
        } else {
            HttpResult.success(dataFromDb)
        }
    }
}