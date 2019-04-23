package bloder.com.presentation.state

import bloder.com.data.values.Post
import bloder.com.data.values.User

sealed class HomeState : State {

    class ShowContent(posts: List<Post>, users: List<User>) : HomeState()
}