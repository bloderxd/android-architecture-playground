package bloder.com.domain

import bloder.com.data.result.http_result.HttpResult
import bloder.com.data.result.http_result.flatMap
import bloder.com.data.values.Post
import bloder.com.repository.Repository

class PostInteractor(private val repository: Repository) {

    suspend fun getPosts() : List<Post> = repository.postRepository().getPosts().flatMap {
        HttpResult.success(it.map { it.toValue() })
    }.getResponse()
}