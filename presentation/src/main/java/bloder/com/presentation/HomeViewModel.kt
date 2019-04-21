package bloder.com.presentation

import bloder.com.data.values.Post
import bloder.com.data.values.User
import bloder.com.domain.PostInteractor
import bloder.com.domain.UserInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val postInteractor: PostInteractor, private val userInteractor: UserInteractor) : BaseViewModel() {

    fun getHomeContent() = launch {
        val posts = getAsync { withContext(Dispatchers.IO) { postInteractor.getPosts() } } onThrow { listOf() }
        val users = getAsync { withContext(Dispatchers.IO) { userInteractor.getUsers() } } onThrow { listOf() }
        handleHomeContent(posts, users)
    }

    private fun handleHomeContent(posts: List<Post>?, users: List<User>?) {
        // Handle and send state to its view!
    }
}