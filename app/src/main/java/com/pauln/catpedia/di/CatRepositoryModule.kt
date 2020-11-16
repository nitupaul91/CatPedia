package com.pauln.catpedia.di

import com.pauln.catpedia.data.CatRepositoryImpl
import com.pauln.catpedia.domain.data.CatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class CatRepositoryModule {

    @Binds
    abstract fun bindsCatRepository(catRepositoryImpl: CatRepositoryImpl): CatRepository
}