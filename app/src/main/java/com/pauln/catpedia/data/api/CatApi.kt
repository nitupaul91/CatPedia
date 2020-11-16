package com.pauln.catpedia.data.api

import com.pauln.catpedia.data.api.response.CatImageResponse
import com.pauln.catpedia.data.api.response.CatResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://api.thecatapi.com/"
const val API_KEY = "ff022ccc-3be4-4778-b40a-05d498c7ff92"
const val X_API_KEY_HEADER = "x-api-key"

interface CatApi {

    @GET("/v1/breeds")
    fun getCats(): Single<List<CatResponse>>

    @GET("/v1/images/search")
    fun getCatImage(@Query("id") id: String): Single<List<CatImageResponse>>
}