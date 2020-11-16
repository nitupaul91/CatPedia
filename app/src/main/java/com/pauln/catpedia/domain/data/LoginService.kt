package com.pauln.catpedia.domain.data

import com.pauln.catpedia.domain.model.Credentials
import io.reactivex.Completable

interface LoginService {

    fun login(credentials: Credentials): Completable
}