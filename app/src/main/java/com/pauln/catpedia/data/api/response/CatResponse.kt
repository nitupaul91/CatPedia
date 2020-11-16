package com.pauln.catpedia.data.api.response

import com.google.gson.annotations.SerializedName

data class CatResponse(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("temperament") val temperament: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("wikipedia_url") val wikiLink: String,
    @SerializedName("id") val id: String
)