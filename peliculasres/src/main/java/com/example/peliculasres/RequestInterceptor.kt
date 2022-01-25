package com.example.peliculasres

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request().url()
            .newBuilder()
            .addQueryParameter("api_key", Constant.API_KEY)
            .addQueryParameter("language","es-ES")
            .build()
        var request = chain.request()
        request = request.newBuilder().url(originalRequest)
            .addHeader("Content-type","application/json")
            .addHeader("Accept","application/json")
            .build()

        return chain.proceed(request)

    }
}