package bloder.com.networking.providers

import bloder.com.networking.network

fun jsonPlaceHolderNetwork() = network {
    baseUrl("https://jsonplaceholder.typicode.com")
}