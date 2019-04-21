package bloder.com.domain

import bloder.com.data.result.http_result.HttpResult
import bloder.com.data.result.http_result.flatMap
import bloder.com.data.values.User
import bloder.com.repository.Repository

class UserInteractor(private val repository: Repository) {

    suspend fun getUsers() : List<User> = repository.userRepository().getUsers().flatMap {
        HttpResult.success(it.map { it.toValue() })
    }.getResponse()
}