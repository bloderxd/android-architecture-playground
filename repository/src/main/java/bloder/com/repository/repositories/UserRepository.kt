package bloder.com.repository.repositories

import bloder.com.data.payloads.UserPayload
import bloder.com.data.requests.PostRequests
import bloder.com.data.result.http_result.HttpResult
import bloder.com.networking.openConnection
import bloder.com.networking.providers.jsonPlaceHolderNetwork
import bloder.com.repository.resources.UserResource

class UserRepository : UserResource {

    private val postRequests: PostRequests = jsonPlaceHolderNetwork().openConnection()

    override suspend fun getUsers(): HttpResult<List<UserPayload>> = try {
        HttpResult.success(postRequests.getUsers().await())
    } catch (e: Exception) {
        HttpResult.requestError(errorBody = e)
    }
}