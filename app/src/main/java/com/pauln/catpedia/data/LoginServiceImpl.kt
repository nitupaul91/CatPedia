package com.pauln.catpedia.data

import com.pauln.catpedia.domain.data.LoginService
import com.pauln.catpedia.domain.model.Credentials
import io.reactivex.Completable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

const val VALID_USERNAME = "garfield"
const val VALID_PASSWORD = "Test123"
const val INCORRECT_CREDENTIALS_ERROR_MESSAGE = "Incorrect username or password. Please try again"
const val DELAY = 1500L

class LoginServiceImpl @Inject constructor() : LoginService {

    override fun login(credentials: Credentials): Completable {
        return Completable.timer(DELAY, TimeUnit.MILLISECONDS)
            .andThen(
                if (areCredentialsCorrect(credentials)) {
                    Completable.complete()
                } else {
                    Completable.error(Throwable(INCORRECT_CREDENTIALS_ERROR_MESSAGE))
                }
            )
    }

    private fun areCredentialsCorrect(credentials: Credentials) =
        credentials.password == VALID_PASSWORD &&
                credentials.username == VALID_USERNAME

}