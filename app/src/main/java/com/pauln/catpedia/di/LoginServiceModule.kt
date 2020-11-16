package com.pauln.catpedia.di

import com.pauln.catpedia.data.LoginServiceImpl
import com.pauln.catpedia.domain.data.LoginService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
abstract class LoginServiceModule {

    @Binds
    abstract fun bindLoginService(loginServiceImpl: LoginServiceImpl): LoginService
}