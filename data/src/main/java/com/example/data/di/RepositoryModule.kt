package com.example.data.di

import android.content.Context
import com.example.data.api.ApiService
import com.example.data.repository.UsersRepositoryImpl
import com.example.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun providePicsumRepository(
        @ApplicationContext context: Context,
        service: ApiService,
    ): UsersRepository {
        return UsersRepositoryImpl(context, service)
    }
}