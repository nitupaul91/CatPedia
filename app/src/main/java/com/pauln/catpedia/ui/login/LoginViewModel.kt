package com.pauln.catpedia.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pauln.catpedia.R
import com.pauln.catpedia.domain.model.Credentials
import com.pauln.catpedia.domain.usecases.LoginUseCase
import com.pauln.catpedia.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class LoginViewModel @ViewModelInject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val disposable = CompositeDisposable()

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val snackbarText = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    val openCatListEvent = SingleLiveEvent<Any>()

    fun login() {
        isLoading.value = true

        disposable.add(
            loginUseCase.login(Credentials(username.value, password.value))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate { isLoading.value = false }
                .subscribe({
                    openCatListEvent.call()
                },
                    { throwable ->
                        snackbarText.value = throwable.localizedMessage
                    })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}