package com.pauln.catpedia.di

import com.pauln.catpedia.data.api.CatApi
import com.pauln.catpedia.data.api.RetrofitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class CatApiModule {

    @Provides
    fun providesCatApi(): CatApi {
        return RetrofitFactory.getRetrofitInstance().create(
            CatApi::
            class.java
        )
    }
}