package com.pauln.catpedia.ui.catdetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pauln.catpedia.domain.model.Cat
import com.pauln.catpedia.util.SingleLiveEvent

class CatDetailsViewModel @ViewModelInject constructor() : ViewModel() {

    val navigateBackEvent = SingleLiveEvent<Any>()

    fun onNavigationBackClicked() {
        navigateBackEvent.call()
    }
}