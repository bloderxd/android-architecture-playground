package bloder.com.repository

import bloder.com.repository.repositories.PostRepository
import bloder.com.repository.repositories.UserRepository
import bloder.com.repository.resources.PostResource
import bloder.com.repository.resources.UserResource

fun production() = object : ResourcesModule {
    override fun userRepository(): UserResource = UserRepository()
    override fun postRepository(): PostResource = PostRepository()
}

fun fake() = object : ResourcesModule {
    override fun userRepository(): UserResource = UserRepository()
    override fun postRepository(): PostResource = PostRepository()
}

interface ResourcesModule {
    fun userRepository() : UserResource
    fun postRepository() : PostResource
}