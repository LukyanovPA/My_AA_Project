package com.pavellukyanov.myaaproject.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Router {
    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url
            .newBuilder()
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val okHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .build()

    private fun goAPI(): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val movieApi: MovieAPI = goAPI().create(MovieAPI::class.java)

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val API_KEY_VALUE = "8a6444939a974846cb13a2ec5853c60a"
        private const val API_KEY = "api_key"
    }
}