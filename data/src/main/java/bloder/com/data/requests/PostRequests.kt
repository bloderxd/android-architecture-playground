package bloder.com.data.requests

import bloder.com.data.payloads.PostPayload
import bloder.com.data.payloads.UserPayload
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface PostRequests {

    @GET("posts")
    fun getPosts() : Deferred<List<PostPayload>>

    @GET("users")
    fun getUsers() : Deferred<List<UserPayload>>
}