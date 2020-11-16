package com.pauln.catpedia

import com.pauln.catpedia.domain.data.LoginService
import com.pauln.catpedia.domain.model.Credentials
import com.pauln.catpedia.domain.usecases.INVALID_INPUT
import com.pauln.catpedia.domain.usecases.LoginUseCase
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class LoginUseCaseTest {

    @InjectMocks
    lateinit var loginUseCase: LoginUseCase

    @Mock
    lateinit var loginService: LoginService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun nullOrEmptyUsername_returnsError() {
        val credentials = Credentials("", "Test123")

        loginUseCase.login(credentials)
            .test()
            .assertErrorMessage(INVALID_INPUT)
    }

    @Test
    fun nullOrEmptyPassword_returnsError() {
        val credentials = Credentials("garfield", "")

        loginUseCase.login(credentials)
            .test()
            .assertErrorMessage(INVALID_INPUT)
    }

    @Test
    fun loginService_returnsComplete() {
        val credentials = Credentials("garfield", "Test123")
        `when`(loginService.login(credentials))
            .thenReturn(Completable.complete())

        loginUseCase.login(credentials)
            .test()
            .assertComplete()
    }

    @Test
    fun loginService_returnsError() {
        val credentials = Credentials("random", "pass")
        val loginErrorMessage = "Login failed"

        `when`(loginService.login(credentials))
            .thenReturn(Completable.error(Throwable(loginErrorMessage)))

        loginUseCase.login(credentials)
            .test()
            .assertErrorMessage(loginErrorMessage)
    }
}