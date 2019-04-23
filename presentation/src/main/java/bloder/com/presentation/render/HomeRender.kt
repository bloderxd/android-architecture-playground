package bloder.com.presentation.render

import bloder.com.data.values.Post
import bloder.com.data.values.User
import bloder.com.presentation.state.HomeState

class HomeRender(private val view: ViewRenderer) {

    suspend fun showHomeContent(posts: List<Post>, users: List<User>) {
        view.render(HomeState.ShowContent(posts, users))
    }
}