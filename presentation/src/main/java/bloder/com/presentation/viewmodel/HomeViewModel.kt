package bloder.com.presentation.viewmodel

import bloder.com.data.result.http_result.HttpCodeException
import bloder.com.data.result.http_result.composeError
import bloder.com.data.values.Post
import bloder.com.data.values.User
import bloder.com.domain.PostInteractor
import bloder.com.domain.UserInteractor
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(private val postInteractor: PostInteractor, private val userInteractor: UserInteractor) : BaseViewModel() {

    fun getHomeContent() = launch {
        val posts = getOnBackground { postInteractor.getPosts().composeError(::handleHttpError).getResponse() } onThrow { listOf() }
        val users = getOnBackground { userInteractor.getUsers() } onThrow { listOf() }
        handleHomeContent(posts, users)
    }

    private fun handleHttpError(exception: Exception) = if (exception is HttpCodeException) {

    } else {}

    private fun handleHomeContent(posts: List<Post>?, users: List<User>?) {
        // Handle and send state to its view!
    }
}