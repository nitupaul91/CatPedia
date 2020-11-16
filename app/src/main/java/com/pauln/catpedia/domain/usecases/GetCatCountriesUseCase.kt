package com.pauln.catpedia.domain.usecases

import com.pauln.catpedia.domain.data.CatRepository
import javax.inject.Inject

class GetCatCountriesUseCase @Inject constructor(
    private val catRepository: CatRepository
) {

    fun getCatCountries() = catRepository.getCatCountries()
}