package com.pauln.catpedia.ui.catlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pauln.catpedia.R
import com.pauln.catpedia.domain.model.Cat
import com.pauln.catpedia.domain.model.Country
import com.pauln.catpedia.domain.usecases.GetCatsUseCase
import com.pauln.catpedia.domain.usecases.GetCatCountriesUseCase
import com.pauln.catpedia.domain.usecases.GetCatsByCountryUseCase
import com.pauln.catpedia.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CatListViewModel @ViewModelInject constructor(
    private val getCatsUseCase: GetCatsUseCase,
    private val getCatCountriesUseCase: GetCatCountriesUseCase,
    private val getCatsByCountryUseCase: GetCatsByCountryUseCase
) : ViewModel() {

    private val disposable = CompositeDisposable()

    val cats = MutableLiveData<List<Cat>>()
    val countries = MutableLiveData<List<Country>>()

    val isPlaceholderVisible = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()
    val isFilterVisible = MutableLiveData<Boolean>()
    val snackbarTextId = MutableLiveData<Int>()

    val openCatFilterEvent = SingleLiveEvent<Any>()
    val openCatDetailsEvent = SingleLiveEvent<Cat>()

    init {
        loadCats()

        isPlaceholderVisible.value = setPlaceholderVisibility()
    }

    private fun loadCats(country: Country? = null) {
        isLoading.value = true

        val catsSingle = if (country == null) {
            getCatsUseCase.getAllCats()
        } else {
            getCatsByCountryUseCase.getCatsByCountry(country)
        }

        disposable.clear()
        disposable.add(
            catsSingle.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate { isLoading.value = false }
                .subscribe({ catList ->
                    cats.value = catList
                    isFilterVisible.value = true
                    isPlaceholderVisible.value = setPlaceholderVisibility()
                }, {
                    snackbarTextId.value = R.string.general_error
                })
        )
    }

    fun filterCatsByCountry(country: Country? = null) {
        loadCats(country)
    }

    fun clearCountryFilter() {
        filterCatsByCountry()
    }

    fun getCatCountries() {
        countries.value = getCatCountriesUseCase.getCatCountries()
        openCatFilterEvent.call()
    }

    fun openCatDetails(cat: Cat) {
        openCatDetailsEvent.value = cat
        openCatDetailsEvent.call()
    }

    private fun setPlaceholderVisibility() =
        cats.value.isNullOrEmpty()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}