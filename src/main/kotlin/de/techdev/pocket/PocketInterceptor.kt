package de.techdev.pocket

import okhttp3.Interceptor
import okhttp3.Response


internal object PocketInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        builder.header("X-Accept", "application/json")
        builder.header("Content-Type", "application/json; charset=UTF-8")

        return chain.proceed(builder.build())
    }

}
