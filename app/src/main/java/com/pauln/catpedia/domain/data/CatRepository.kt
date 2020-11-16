package com.pauln.catpedia.domain.data

import com.pauln.catpedia.domain.model.Cat
import com.pauln.catpedia.domain.model.Country
import io.reactivex.Single

interface CatRepository {

    fun getAllCats(): Single<List<Cat>>

    fun getCatsByCountry(country: Country): Single<List<Cat>>

    fun getCatCountries(): List<Country>
}