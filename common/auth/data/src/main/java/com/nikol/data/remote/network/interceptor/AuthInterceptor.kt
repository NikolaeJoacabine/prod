package com.nikol.data.remote.network.interceptor

import android.content.Context
import com.nikol.data.remote.network.target.NoAuth
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val method = request.tag(Invocation::class.java)?.method()
        val isNoAuth = method?.getAnnotation(NoAuth::class.java) != null

        val newRequest = if (!isNoAuth) {
            val token = {}
            request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            request
        }

        return chain.proceed(newRequest)
    }
}