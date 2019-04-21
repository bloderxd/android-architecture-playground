package bloder.com.networking

import okhttp3.Interceptor

private typealias BaseUrl = String

class NetworkProvider {

    private lateinit var api: BaseUrl
    private lateinit var interceptors: Array<out Interceptor>

    fun baseUrl(api: BaseUrl) : NetworkProvider = buildWith {
        this.api = api
    }

    fun interceptors(vararg interceptors: Interceptor) : NetworkProvider = buildWith {
        this.interceptors = interceptors
    }

    fun provideNetworkDetails(): NetworkInformation = NetworkInformation(api, interceptors.toList())

    private fun buildWith(changing: () -> Unit) : NetworkProvider = this.also { changing() }
}

data class NetworkInformation(val api: BaseUrl, val interceptors: List<Interceptor>)

fun network(build: NetworkProvider.() -> Unit) : NetworkProvider = NetworkProvider().also {
    it.build()
}