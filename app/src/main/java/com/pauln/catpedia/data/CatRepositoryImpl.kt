package com.pauln.catpedia.data

import com.pauln.catpedia.data.api.CatApi
import com.pauln.catpedia.domain.data.CatRepository
import com.pauln.catpedia.domain.model.Cat
import com.pauln.catpedia.domain.model.Country
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatRepositoryImpl @Inject constructor(private val catApi: CatApi) : CatRepository {

    private val cachedCats = mutableListOf<Cat>()

    override fun getAllCats(): Single<List<Cat>> {
        if (cachedCats.isNotEmpty()) {
            return Single.just(cachedCats)
        }

        return catApi.getCats()
            .flatMapObservable { catResponse -> Observable.fromIterable(catResponse) }
            .flatMapSingle { catResponseItem ->
                catApi.getCatImage(catResponseItem.id)
                    .subscribeOn(Schedulers.io())
                    .map { catImgResponse ->
                        Cat(
                            catResponseItem.name,
                            catResponseItem.description,
                            catResponseItem.countryCode,
                            catResponseItem.temperament,
                            catResponseItem.wikiLink,
                            catResponseItem.id,
                            catImgResponse[0].url
                        )
                    }
            }
            .toList()
            .doOnSuccess(this::cacheCats)
    }

    private fun cacheCats(cats: MutableList<Cat>) {
        cachedCats.addAll(cats)
    }

    override fun getCatsByCountry(country: Country): Single<List<Cat>> {
        return getAllCats()
            .map { cats -> cats.filter { it.countryCode == country.code } }
    }

    override fun getCatCountries() = cachedCats.map { Country(it.countryCode) }.distinct()
}
