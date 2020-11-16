package com.pauln.catpedia.domain.usecases

import com.pauln.catpedia.domain.data.LoginService
import com.pauln.catpedia.domain.model.Credentials
import io.reactivex.Completable
import javax.inject.Inject

const val INVALID_INPUT = "Please enter your username and password"

class LoginUseCase @Inject constructor(
    private val loginService: LoginService
) {

    fun login(credentials: Credentials): Completable {
        return if (areFieldsInvalid(credentials.username, credentials.password)) {
            Completable.error(Throwable(INVALID_INPUT))
        } else {
            loginService.login(credentials)
        }
    }

    private fun areFieldsInvalid(username: String?, password: String?) =
        (username.isNullOrEmpty() || password.isNullOrEmpty())
}