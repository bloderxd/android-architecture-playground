package bloder.com.networking

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

inline fun <reified T> NetworkProvider.openConnection() : T = with(this.provideNetworkDetails()) {
    createConnectionWith(api, interceptors.toTypedArray()).create(T::class.java)
}

fun createConnectionWith(api: String, interceptors: Array<out Interceptor>): Retrofit = with(Retrofit.Builder()) {
    addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
    client(okHttpClient(interceptors))
    baseUrl(api)
    build()
}

private fun okHttpClient(interceptors: Array<out Interceptor>) = with(OkHttpClient.Builder()) {
    interceptors.forEach { addInterceptor(it) }
    addInterceptor(HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY })
    connectTimeout(30, TimeUnit.SECONDS)
    readTimeout(30, TimeUnit.SECONDS)
    build()
}