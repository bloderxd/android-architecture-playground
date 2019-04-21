package bloder.com.repository.resources

import bloder.com.data.payloads.PostPayload
import bloder.com.data.result.http_result.HttpResult

interface PostResource {

    suspend fun getPosts() : HttpResult<List<PostPayload>>
}