package bloder.com.data.payloads

import bloder.com.data.values.Post

data class PostPayload(
        private val userId: Long,
        private val id: Long,
        private val title: String,
        private val body: String
) {

    fun toValue(): Post = Post(userId, id, title, body)
}