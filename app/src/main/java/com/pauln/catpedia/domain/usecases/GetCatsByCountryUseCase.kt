package com.pauln.catpedia.domain.usecases

import com.pauln.catpedia.domain.data.CatRepository
import com.pauln.catpedia.domain.model.Cat
import com.pauln.catpedia.domain.model.Country
import io.reactivex.Single
import javax.inject.Inject

class GetCatsByCountryUseCase @Inject constructor(
    private val catRepository: CatRepository
)  {

    fun getCatsByCountry(country: Country): Single<List<Cat>> {
        return catRepository.getCatsByCountry(country)
            .flatMap(this::sortCatsAlphabetically)
    }

    private fun sortCatsAlphabetically(cats: List<Cat>) =
        Single.just(cats.sortedBy { cat -> cat.name })
}