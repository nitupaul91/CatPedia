package com.pauln.catpedia.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cat(
    val name: String?,
    val description: String?,
    val countryCode: String,
    val temperament: String?,
    val wikiLink: String?,
    val id: String,
    val image: String? = null
) : Parcelable