package bloder.com.repository.repositories

import bloder.com.data.payloads.PostPayload
import bloder.com.data.requests.PostRequests
import bloder.com.data.result.http_result.HttpResult
import bloder.com.networking.openConnection
import bloder.com.networking.providers.jsonPlaceHolderNetwork
import bloder.com.repository.resources.PostResource

class PostRepository : PostResource {

    private val postRequests: PostRequests = jsonPlaceHolderNetwork().openConnection()

    override suspend fun getPosts(): HttpResult<List<PostPayload>> = try {
        HttpResult.success(postRequests.getPosts().await())
    } catch (e: Exception) {
        HttpResult.requestError(errorBody = e)
    }
}