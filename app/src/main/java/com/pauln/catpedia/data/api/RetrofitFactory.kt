package com.pauln.catpedia.data.api

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitFactory {

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(getClient())
            .build()
    }

    private fun getClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor {
            val request: Request =
                it.request().newBuilder().addHeader(X_API_KEY_HEADER, API_KEY).build()
            it.proceed(request)
        }
        return builder.build()
    }
}
