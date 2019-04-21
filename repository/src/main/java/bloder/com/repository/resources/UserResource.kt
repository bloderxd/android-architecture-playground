package bloder.com.repository.resources

import bloder.com.data.payloads.UserPayload
import bloder.com.data.result.http_result.HttpResult

interface UserResource {

    suspend fun getUsers() : HttpResult<List<UserPayload>>
}