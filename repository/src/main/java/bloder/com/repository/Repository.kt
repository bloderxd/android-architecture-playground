package bloder.com.repository

typealias Repository = ResourcesModule

fun repository() : ResourcesModule = production()

fun ResourcesModule.test() = fake()